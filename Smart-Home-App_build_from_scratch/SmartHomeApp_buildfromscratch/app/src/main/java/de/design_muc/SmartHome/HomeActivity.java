package de.design_muc.SmartHome;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Alarmanlage;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.TV;

public class HomeActivity extends BaseActivity {


    // //variables
    private DatabaseReference myRef;
    private boolean defaultValue;
    Switch SwitchAlarm;
    Switch SwitchTV;
    SeekBar seekBarHeizung;



    // sensor-variables
    private Alarmanlage myAlarmanlage;
    private TV mySmartTV;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        seekBarHeizung = (SeekBar) findViewById(R.id.seekBar2);


        //Firebasedatebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();



        //create sensors: TV and Alarmanlage

        mySmartTV= new TV ();
        myAlarmanlage = Alarmanlage.getInstance();


        // initialize switches

        SwitchTV = (Switch)findViewById(R.id.switchTV);
        SwitchAlarm = (Switch)findViewById(R.id.switch_alarmanlage);


        // set OnCheckedChangeListener for TV and Alarmanlage Switches
        SwitchTV.setOnCheckedChangeListener(myTVOnCheckedChangeListener);
        SwitchAlarm.setOnCheckedChangeListener(myAlarmOnCheckedChangeListener);

        final TextView timerview = (TextView) findViewById(R.id.wunschtemp);
        timerview.setMaxEms(30);

        seekBarHeizung.setProgress(21);


        //Firebasedatebase EventListener

        myRef.child("Settings").child("LivingroomDefaultValue").addValueEventListener(myValueEventListener);
        myRef.child("wohnzimmer").child("tv").addValueEventListener(myTVValueEventListener);
        myRef.child("Global_values").child("alarmanlage").addValueEventListener(myAlarmValueEventListener);


        checkTVStatus();


        seekBarHeizung.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBarHeizung, int progress, boolean b) {

                int grad = (int) progress;

                String gradStrings = Integer.toString(grad);

                 timerview.setText(gradStrings);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    //  ValueEventListener for sensor-alarmanlage

    ValueEventListener myAlarmValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            Boolean myAlarmStatusDB = (Boolean) dataSnapshot.getValue();
            myAlarmanlage.setStatus(myAlarmStatusDB);

            if(myAlarmanlage.getStatus()==false){
                SwitchAlarm.setChecked(false);

            }
            else {
                SwitchAlarm.setChecked(true);

            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };





    // OnCheckedChangeListener for alarmanlage


    CompoundButton.OnCheckedChangeListener myAlarmOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub


            if(isChecked)
            {
                SwitchAlarm.setText("An");
                myAlarmanlage.setStatus(true);
                myRef.child("Global_values").child("alarmanlage").setValue(true);

            }
            else {
                SwitchAlarm.setText("Aus");
                myAlarmanlage.setStatus(false);
                myRef.child("Global_values").child("alarmanlage").setValue(false);

            }
        }
    };





// ValueEventListener for defaultvalue


     ValueEventListener myValueEventListener = new ValueEventListener(){
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {


                 defaultValue = (Boolean) dataSnapshot.getValue();

                 if(!defaultValue) {
                     showPopup();
                 }


         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
             // ...
         }
     };


// ValueEventListener for mytv

    ValueEventListener myTVValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

          //  if(dataSnapshot.getValue() != null && dataSnapshot.getValue(Boolean.class)){
               Boolean tvStatusDB = (Boolean) dataSnapshot.getValue();
               mySmartTV.setStatus(tvStatusDB);
                checkTVStatus();
          //  }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };





    // oncheckChangelistener  TV


    CompoundButton.OnCheckedChangeListener myTVOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub


            if(isChecked)
            {
                SwitchTV .setText("An");
                myRef.child("wohnzimmer").child("tv").setValue(true);

            }
            else {
                SwitchTV .setText("Aus");
                myRef.child("wohnzimmer").child("tv").setValue(false);

            }
        }
    };






    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }




    private void checkTVStatus(){

        boolean status;
        status = mySmartTV.getStatus();

        if(status){

            SwitchTV .setText("An");
            SwitchTV.setChecked(true);

        }
        else {

            SwitchTV .setText("Aus");
            SwitchTV.setChecked(false);
        }

    }





    private void showPopup() {
        new AlertDialog.Builder(HomeActivity.this)
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
