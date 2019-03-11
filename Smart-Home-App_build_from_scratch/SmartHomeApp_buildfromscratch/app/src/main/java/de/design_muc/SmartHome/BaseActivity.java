package de.design_muc.SmartHome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Alarmanlage;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;
    private FirebaseAuth auth;
    String name;
    int selectedMenu;

    //SharedPreferences für Defaultwerte
    SharedPreferences sharedpreferences;
    public static final String mypreference = "de.design_muc.SmartHome";


    //Alarmanalge








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);



        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();




        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }




    }

    //action bar menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navi_top,menu);
        return super.onCreateOptionsMenu(menu);
    }


    //handler for action bar menu


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logout){

            signOut();
        }

        else if (id==R.id.settings) {

            startActivity(new Intent(this, SettingsActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();

    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        navigationView.postDelayed(() -> {

            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {

                startActivity(new Intent(this, HomeActivity.class));

            } else if (itemId == R.id.navigation_dashboard) {
                startActivity(new Intent(this, SchlafzimmerActivity.class));

            } else if (itemId == R.id.navigation_notifications) {
                startActivity(new Intent(this, OfficeActivity.class));

            }
            finish();

        }, 300);

        return true;

    }





    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        MenuItem item = navigationView.getMenu().findItem(itemId);
        item.setChecked(true);
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();

private void setSelectedMenu (){
    selectedMenu=1;

}


    private void signOut() {
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