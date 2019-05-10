package de.design_muc.SmartHome;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.List;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Benutzerlokalisierung;

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,BeaconConsumer {

    protected BottomNavigationView navigationView;
    private FirebaseAuth auth;
    String name;
    boolean gotoMenu;
    String raum;
    boolean alreadyExecuted;

    private Dialog changeRoomDialog;
    private static final String TAG = "BaseActivity";
    private List<Beacon> beaconList;
    private BeaconManager beaconManager;


    //SharedPreferences für Defaultwerte
    SharedPreferences sharedpreferences;
    public static final String mypreference = "de.design_muc.SmartHome";

    private Benutzerlokalisierung myGPSOrtung;
    private Beacon closestBeacon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                });
                builder.show();
            }
        }
        myGPSOrtung = new Benutzerlokalisierung(this, "");

        //Ibeacon
        this.beaconList = new ArrayList<>();
        this.beaconManager = BeaconManager.getInstanceForApplication(this);
        this.beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        this.beaconManager.bind(this);
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
                //TODO
                closestBeacon = null;
                if (beacons.size() > 0) {
                    beaconList.clear();
                    for (Iterator<Beacon> iterator = beacons.iterator(); iterator.hasNext(); ) {
                        Beacon currentDetectedBeacon = iterator.next();
                        beaconList.add(currentDetectedBeacon);
                        Log.v(TAG, "Beacon "+ currentDetectedBeacon.getId3() + "; Distance to beacon: "+  currentDetectedBeacon.getDistance() + "; signal strength : " + currentDetectedBeacon.getRssi());
                    }
                    for(Beacon currentBeacon : beaconList){
                        if(closestBeacon == null){
                            closestBeacon = currentBeacon;
                        } else if(closestBeacon.getId3() != currentBeacon.getId3() && closestBeacon.getRssi() > currentBeacon.getRssi()){
                            int doubleComaprison = Double.compare(closestBeacon.getDistance(), currentBeacon.getDistance());
                            Log.v(TAG, "Value of Double.compare: " + doubleComaprison + "; Value of \"normal\" compare: " + Boolean.toString(closestBeacon.getRssi() > currentBeacon.getRssi()));
                            alreadyExecuted = false;
                            closestBeacon = currentBeacon;
                        }
                    }
                    if(!alreadyExecuted) {
                        alreadyExecuted = true;
                        roomDetected(closestBeacon.getId3().toInt());
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
        if(changeRoomDialog == null || !changeRoomDialog.isShowing()) {
            if (nr == 8 && !this.getClass().getSimpleName().equals("HomeActivity")) {
                raum = "Wohnzimmer";
                showChangeRoomActivityDialog();
            } else if (nr == 7 && !this.getClass().getSimpleName().equals("SchlafzimmerActivity")) {
                raum = "Schlafzimmer";
                showChangeRoomActivityDialog();
            } else if (nr == 5 && !this.getClass().getSimpleName().equals("OfficeActivity")) {
                raum = "Büro";
                showChangeRoomActivityDialog();
            }
        }

    }

    private void showChangeRoomActivityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Raum '" + raum + "' erkannt. Wollen Sie zu dieser Raumansicht wechseln?");

        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setActivity(raum);

            }
        });
        this.changeRoomDialog = builder.show();
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
        } else if (id==R.id.settings) {
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