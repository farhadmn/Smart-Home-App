package de.design_muc.SmartHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private DatabaseReference myRef;
    Switch Switch1, Switch2, Switch3;
    boolean defaultValue;
    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        myRef.child("Settings").child("BedRoomDefaulValue").addValueEventListener(Switch1_ValueEventListener);
        myRef.child("Settings").child("LivingroomDefaultValue").addValueEventListener(Switch2_ValueEventListener);
        myRef.child("Settings").child("OfficeDefaultValue").addValueEventListener(Switch3_ValueEventListener);

        Switch1 = (Switch) findViewById(R.id.switch1);
        Switch2 = (Switch) findViewById(R.id.switch2);
        Switch3 = (Switch) findViewById(R.id.switch3);

        Switch1.setOnCheckedChangeListener(Switch1_OnCheckedChangeListener);
        Switch2.setOnCheckedChangeListener(Switch2_OnCheckedChangeListener);
        Switch3.setOnCheckedChangeListener(Switch3_OnCheckedChangeListener);

    }

    // switch 1
    ValueEventListener Switch1_ValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            defaultValue = (Boolean) dataSnapshot.getValue();

            if (defaultValue) {
                Switch1.setChecked(true);
            } else {
                Switch1.setChecked(false);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // switch1
    CompoundButton.OnCheckedChangeListener Switch1_OnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                myRef.child("Settings").child("BedRoomDefaulValue").setValue(true);
            } else {
                myRef.child("Settings").child("BedRoomDefaulValue").setValue(false);
            }
        }
    };

    // switch 2
    ValueEventListener Switch2_ValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            defaultValue = (Boolean) dataSnapshot.getValue();
            if (defaultValue) {
                Switch2.setChecked(true);
           } else {
                Switch2.setChecked(false);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // switch2
    CompoundButton.OnCheckedChangeListener Switch2_OnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                myRef.child("Settings").child("LivingroomDefaultValue").setValue(true);
            } else {
                myRef.child("Settings").child("LivingroomDefaultValue").setValue(false);
            }
        }
    };

    // switch 3
    ValueEventListener Switch3_ValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            defaultValue = (Boolean) dataSnapshot.getValue();
            if (defaultValue) {
                Switch3.setChecked(true);
            } else {
                Switch3.setChecked(false);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // switch3
    CompoundButton.OnCheckedChangeListener Switch3_OnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                myRef.child("Settings").child("OfficeDefaultValue").setValue(true);
            } else {
                myRef.child("Settings").child("OfficeDefaultValue").setValue(false);
            }
        }
    };

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
            Toast.makeText(getApplicationContext(), "Kein Benutzer ist eingeloggt.",
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