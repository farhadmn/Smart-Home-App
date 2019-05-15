package de.design_muc.SmartHome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.ContextDescription;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.DruckerSensor;

public class TodoListeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private ArrayList<String> TodoList;
    DruckerSensor myDrucker;
    private DatabaseReference myRef;
    protected BottomNavigationView navigationView;
    private ListView TodoListView;
    private ArrayAdapter<String> adapter;
    private ContextDescription myContextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_liste);
        myContextDescription = ContextDescription.getInstance();
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        TodoListView = (ListView) findViewById(R.id.todoListView);
        myContextDescription = ContextDescription.getInstance();
        TodoList = new ArrayList<String>();
        TodoList = myContextDescription.getTodos();
        myDrucker = DruckerSensor.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.TodoList);
        this.TodoListView.setAdapter(adapter);

        TodoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                deleteEintrag(pos);
                return true;
            }
        });
    }

    public void deleteEintrag(final int pos) {

        String name;
        name = TodoList.get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Wollen Sie diesen Eintrag:  " + name + " wirklich löschen?");

        builder.setNegativeButton("Nein", (dialogInterface, i) -> dialogInterface.cancel());

        builder.setPositiveButton("Ja", (dialogInterface, i) -> {
            myContextDescription.deleteEintrag(pos);
            TodoListView.setAdapter(adapter);
            myDrucker.setStatusDrucker("normal");
            myRef.child("office").child("drucker").setValue("normal");
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navi_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //handler for action bar menu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            signOut();
        } else if (id == R.id.todoliste) {
            startActivity(new Intent(this, TodoListeActivity.class));
        } else if (id == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //Firebase logout

    public void signOut() {
        auth.getCurrentUser();
        if (auth.getCurrentUser() == null) {
            Toast.makeText(getApplicationContext(), "Kein Nutzer eingeloggt.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(getApplicationContext(), "Sie wurden erfolgreich abgemeldet!",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.navigation_dashboard) {
                startActivity(new Intent(this, BedRoomActivity.class));
            } else if (itemId == R.id.navigation_notifications) {
                startActivity(new Intent(this, OfficeActivity.class));
            }
            finish();
        }, 300);
        return true;
    }
}


