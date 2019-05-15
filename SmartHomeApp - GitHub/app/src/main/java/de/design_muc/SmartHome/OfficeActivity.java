package de.design_muc.SmartHome;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Calendar;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.ComputedSensor;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.ContextDescription;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.DruckerSensor;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Kalender;

public class OfficeActivity extends BaseActivity {

    private DatabaseReference myRef;
    private boolean defaultValue;

    private TextView druckerStatusTextV;

    private DruckerSensor myDrucker;
    private ArrayList<String> termine;
    private ListView calanderListView;

    private Calendar mycalander;
    private ArrayAdapter<String> adapter;
    private ContextDescription myContextDescription;

    private static final int TITLE = 0;
    private static final int START_DATE = 1;
    private static final int END_DATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        druckerStatusTextV = findViewById(R.id.druckerstatus);

        myRef.child("Settings").child("OfficeDefaultValue").addValueEventListener(myValueEventListener);
        myRef.child("office").child("drucker").addValueEventListener(myDruckerValueEventListener);

        myDrucker = DruckerSensor.getInstance();

        calanderListView = findViewById(R.id.calListView);

        termine = new ArrayList<>();

        mycalander = new Calendar();
        Map<String, List<String>> appointments = mycalander.readCalendarEvent(this);
        for(Map.Entry<String, List<String>> entry: appointments.entrySet()) {
            termine.add(entry.getValue().get(TITLE) + " von " + entry.getValue().get(START_DATE) + " bis " + entry.getValue().get(END_DATE));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.termine);
        this.calanderListView.setAdapter(adapter);

        myContextDescription = ContextDescription.getInstance();

    }

    // ValueEventListener for Drucker
    ValueEventListener myDruckerValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            String sDB = String.valueOf(dataSnapshot.getValue());
            myDrucker.setStatusDrucker(sDB);
            // Toast.makeText(getApplicationContext(), "You pressed Test." +sDB, Toast.LENGTH_LONG).show();

            if (myDrucker.getStatusDrucker().equals("normal")) {
                druckerStatusTextV.setText("normal");
                druckerStatusTextV.setTextColor(Color.parseColor("#33cc33"));

            } else if (myDrucker.getStatusDrucker().equals("Papierleer")) {
                druckerStatusTextV.setText("Papier ist leer");
                druckerStatusTextV.setTextColor(Color.parseColor("#ff4000"));
                myContextDescription.setTodo("Biite Papier einlegen bzw. nachbestellen");
            } else if (myDrucker.getStatusDrucker().equals("tonerleer")) {
                druckerStatusTextV.setText("Toner ist Leer.");
                druckerStatusTextV.setTextColor(Color.parseColor("#ff4000"));
                myContextDescription.setTodo("Biite Toner nachbestellen");
                String test;
                test = myContextDescription.getTodo(0);
                Toast.makeText(getApplicationContext(), "You pressed Test." + test, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    @Override
    int getContentViewId() {
        return R.layout.activity_work;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_notifications;
    }


    // Firebase Listener // check for default value
    ValueEventListener myValueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            defaultValue = (Boolean) dataSnapshot.getValue();
            if (!defaultValue) {
                showPopup();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    //defaultpopup
    private void showPopup() {
        new AlertDialog.Builder(OfficeActivity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.defaultwertmsg))
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Uri uri = Uri.parse("http://smart.design-muc.de/admin");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                })
                .show();
    }

}
