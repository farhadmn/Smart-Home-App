package de.design_muc.SmartHome;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Benutzerlokalisierung;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,BeaconConsumer {

    protected BottomNavigationView navigationView;
    private FirebaseAuth auth;
    String name;
    boolean gotoMenu;
    String raum;
    boolean alreadyExecuted;

    private static final String TAG = "BEACON_TEst";
    private ArrayList<String> beaconList;
    private BeaconManager beaconManager;


    //SharedPreferences für Defaultwerte
    SharedPreferences sharedpreferences;
    public static final String mypreference = "de.design_muc.SmartHome";

    private Benutzerlokalisierung myGPSOrtung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        //super.onStart();


        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }


        myGPSOrtung = new Benutzerlokalisierung(this, "");


        //Ibeacon


        this.beaconList = new ArrayList<String>();
        this.beaconManager = BeaconManager.getInstanceForApplication(this);

        this.beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        this.beaconManager.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                });
                builder.show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        this.beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    beaconList.clear();
                    for (Iterator<Beacon> iterator = beacons.iterator(); iterator.hasNext(); ) {
                        beaconList.add(iterator.next().getId3().toString());
                       String BeaconMinor= beaconList.get(0);


                        int beaconM=Integer.valueOf(BeaconMinor);

                        if(!alreadyExecuted) {
                            roomDetected(beaconM);
                            alreadyExecuted = true;
                        }

                        //roomDetected(beaconM);
                       // Log.v(TAG, "index=" +test);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        });
        try {
            this.beaconManager.startRangingBeaconsInRegion(new Region("MyRegionId", null, null, null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        gotoMenu = false;

       // roomDetected(2);


        //  deleteEintrag();


    }


    public void roomDetected( int nr) {

         alreadyExecuted= true;


        if(nr==8){

            raum="Wohnzimmer";
        }

        if(nr==7){

            raum="Schlafzimmer";
        }

        if(nr==9){

            raum="Büro";
        }





       // Toast.makeText(getApplicationContext(), "You pressed Test."+raum, Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);



        builder.setCancelable(true);
        builder.setMessage("Raumerkannt:  " + " " + raum + " " + ", wollen Sie die Gerätestatus anschauen ?");

        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

        //        setActivity(finalRaum);

                setActivity(raum);

            }
        });
        builder.show();
    }




    public void setActivity (String IbeaconName){

        if (IbeaconName.equals("Wohnzimmer")) {
            if (this.getClass().getSimpleName().equals("HomeActivity")){
                return;
            }
            startActivity(new Intent(this, HomeActivity.class));

        }
        if (IbeaconName.equals("Schlafzimmer")) {
            if (this.getClass().getSimpleName().equals("SchlafzimmerActivity")){

                return;
            }
            startActivity(new Intent(this, SchlafzimmerActivity.class));

        }
        if (IbeaconName.equals("Büro")) {
            if (this.getClass().getSimpleName().equals("OfficeActivity")){
                return;
            }
            startActivity(new Intent(this, OfficeActivity.class));

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


        if (id==R.id.todoliste) {

            startActivity(new Intent(this, TodoListeActivity.class));
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