package de.design_muc.SmartHome;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.List;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.ContextDescription;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.DruckerSensor;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Kalender;

public class TodoListeActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    //private ArrayList<String> TodoList;
    private ArrayList<String> TodoList;
    DruckerSensor myDrucker;
    private DatabaseReference myRef;


    private ListView TodoListView;
    private ArrayAdapter<String> adapter;
    private ContextDescription myContextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_liste);
        myContextDescription = ContextDescription.getInstance();

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
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {


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
        builder.setMessage("Wollen diesen Eintrag:  " + " " + name + " " + "wirklich löschen ?");

        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myContextDescription.deleteEintrag(pos);
                TodoListView.setAdapter(adapter);
                myDrucker.setStatusDrucker("normal");
                myRef.child("office").child("drucker").setValue("normal");
            }
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

        }

        if (id==R.id.todoliste) {

            startActivity(new Intent(this, TodoListeActivity.class));
        }else if (id == R.id.settings) {

            startActivity(new Intent(this, SettingsActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }


    //Firebase logout

    public void signOut() {
        auth.getCurrentUser();
        if (auth.getCurrentUser() == null) {
            Toast.makeText(getApplicationContext(), "No user signed in.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(getApplicationContext(), "Sie wurden erfolgreich abgemeldet!",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}


