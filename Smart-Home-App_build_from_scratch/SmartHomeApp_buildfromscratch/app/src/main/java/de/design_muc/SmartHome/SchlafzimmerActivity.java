package de.design_muc.SmartHome;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Alarmanlage;

public class SchlafzimmerActivity extends BaseActivity {


    //variables

    Switch AlarmanlageSensorSwitch;
    private DatabaseReference myRef;

    // sensor-variables
    private Alarmanlage AlarmanlageSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        //ValueEventListener for Alarmanlage
        myRef.child("Global_values").child("alarmanlage").addValueEventListener(myAlarmValueEventListener);

        AlarmanlageSensorSwitch = (Switch)findViewById(R.id.switch6);

        //create new object from Alarmanlge class
        AlarmanlageSensor = Alarmanlage.getInstance();


        // setOnCheckedChangeListener for AlarmanlageSensorSwitch
        AlarmanlageSensorSwitch.setOnCheckedChangeListener(myAlarmOnCheckedChangeListener);
    }


    // ValueEventListener for Alarmanlage

    ValueEventListener myAlarmValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            //get status from firebasedatebase
            Boolean myAlarmStatusDB = (Boolean) dataSnapshot.getValue();
            //set alarmanlage status
            AlarmanlageSensor.setStatus(myAlarmStatusDB);

            if(AlarmanlageSensor.getStatus()==false){
                AlarmanlageSensorSwitch.setChecked(false);

            }
            else {
                AlarmanlageSensorSwitch.setChecked(true);

            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };





    // OnCheckedChangeListener for AlarmanlageSensorSwitch


    CompoundButton.OnCheckedChangeListener myAlarmOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub


            if(isChecked)
            {
                // set context descrtiption = AlarmanlageON
                AlarmanlageSensorSwitch.setText("An");
                AlarmanlageSensor.setStatus(true);
                myRef.child("Global_values").child("alarmanlage").setValue(true);

            }
            else {
                // set context descrtiption = AlarmanlageOFF
                AlarmanlageSensorSwitch.setText("Aus");
                AlarmanlageSensor.setStatus(false);
                myRef.child("Global_values").child("alarmanlage").setValue(false);

            }
        }
    };




    @Override
    int getContentViewId() {
        return R.layout.activity_bedroom;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_dashboard;

    }


}

