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
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.ClothRecommendation;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.JalousienSteuerung;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.LichtStatus;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.WeatherSensor;

public class BedRoomActivity extends BaseActivity {


    Switch SwitchLicht,SwitchAlarm, SwichtOutfit;
    private DatabaseReference myRef, myRef_J;
    private boolean defaultValue;
    private String recommendation;

    //sensoren
    private LichtStatus myLight;
    private Alarmanlage myAlarmSystem;
    private JalousienSteuerung myJalousienControl;
    private ClothRecommendation myClothRecommendation;
    TextView scale, recommendationTextView;
    SeekBar seekbarjal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child("Settings").child("BedRoomDefaulValue").addValueEventListener(myValueEventListener);
        myRef.child("schlafzimmer").child("licht").addValueEventListener(myLichtValueEventListener);
        myRef.child("schlafzimmer").child("jalousien").addValueEventListener(myjalousientValueEventListener);
        myRef.child("Global_values").child("alarmanlage").addValueEventListener(myAlarmValueEventListener);
        myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").addValueEventListener(myOutfitValueEventListener);

        SwitchLicht = findViewById(R.id.switch3);
        SwitchAlarm = findViewById(R.id.switch6);
        SwichtOutfit = findViewById(R.id.switch7);

        seekbarjal = findViewById(R.id.seekBar6);
        scale = findViewById(R.id.stufen);
        recommendationTextView = findViewById(R.id.textView13);

        //initial. Sensoren
        myLight = new LichtStatus(true);
        myJalousienControl = new JalousienSteuerung(this);
        myAlarmSystem = Alarmanlage.getInstance();
        myClothRecommendation = ClothRecommendation.getInstance(this);
        SwitchLicht.setOnCheckedChangeListener(myLichtOnCheckedChangeListener);
        SwitchAlarm.setOnCheckedChangeListener(myAlarmOnCheckedChangeListener);
        SwichtOutfit.setOnCheckedChangeListener(myOutFitOnCheckedChangeListener);
        seekbarjal.setOnSeekBarChangeListener(myJalOnSeekBarChangeListener);

        myJalousienControl.decisionLogic();
    }

    public void getRecommendation() {
        if (myClothRecommendation.getStatus()) {
            recommendation = myClothRecommendation.getRecommendation();
            if (recommendation != null) {
                recommendationTextView.setText(recommendation);
            } else {
                recommendationTextView.setText("Outfitvorschlag  nicht möglich.");
            }
        } else {
            recommendationTextView.setText("Outfitvorschlag einschalten. ");
        }
    }

    // ValueEventListener for Outfit
    ValueEventListener myOutfitValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Boolean myOutfitStatus = (Boolean) dataSnapshot.getValue();
            myClothRecommendation.setStatus(myOutfitStatus);

            if(myClothRecommendation.getStatus()){
                WeatherSensor.getInstance(BedRoomActivity.this).getWetterValueAPI(BedRoomActivity.this);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // outfit
    CompoundButton.OnCheckedChangeListener myOutFitOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub

            if(isChecked) {
                SwichtOutfit.setText("An");
                myClothRecommendation.setStatus(true);
                myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").setValue(true);
                getRecommendation();

            } else {
                SwichtOutfit.setText("Aus");
                myClothRecommendation.setStatus(false);
                myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").setValue(false);
                recommendationTextView.setText("");
            }
        }
    };

    // ValueEventListener for mytv
    ValueEventListener myAlarmValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Boolean myAlarmStatusDB = (Boolean) dataSnapshot.getValue();
            myAlarmSystem.setStatus(myAlarmStatusDB);

            if(myAlarmSystem.getStatus()){
                SwitchAlarm.setChecked(false);
            } else {
                SwitchAlarm.setChecked(true);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // oncheckChangelistener  Alarmanlage
    CompoundButton.OnCheckedChangeListener myAlarmOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if(isChecked)
            {
                SwitchAlarm.setText("An");
                myAlarmSystem.setStatus(true);
                myRef.child("Global_values").child("alarmanlage").setValue(true);
            } else {
                SwitchAlarm.setText("Aus");
                myAlarmSystem.setStatus(false);
                myRef.child("Global_values").child("alarmanlage").setValue(false);

            }
        }
    };


    // mylicht
    ValueEventListener myLichtValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            Boolean myllchtStatus = (Boolean) dataSnapshot.getValue();
            myLight.setStatus(myllchtStatus);

            if(myLight.getStatus()==false){
                SwitchLicht.setChecked(false);
            } else {
                SwitchLicht.setChecked(true);
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    // myLichtOnCheckedChangeListener
    CompoundButton.OnCheckedChangeListener myLichtOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if(isChecked) {
                SwitchLicht.setText("An");
                myLight.setStatus(true);
                myRef.child("schlafzimmer").child("licht").setValue(true);

            } else {
                SwitchLicht.setText("Aus");
                myLight.setStatus(false);
                myRef.child("schlafzimmer").child("licht").setValue(false);
            }
        }
    };

    // ValueEventListener for myjalousien
    ValueEventListener myjalousientValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //con string to in
            String sDB=  String.valueOf(dataSnapshot.getValue());
            int statusBD=Integer.valueOf(sDB);

            if( statusBD==3){
                seekbarjal.setProgress(3);

            } else if( statusBD==2){
                seekbarjal.setProgress(2);

            } else if( statusBD==1){
                seekbarjal.setProgress(1);

            } else if( statusBD==0){
                 seekbarjal.setProgress(0);

            }
            myJalousienControl.setStatus(statusBD);

        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            // ...
        }
    };

    //jalousien
    SeekBar.OnSeekBarChangeListener myJalOnSeekBarChangeListener= new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekbarjal, int progress, boolean b) {
            int grad = (int) progress;
            String gradStrings = Integer.toString(grad);
            scale.setText(gradStrings);
            myRef.child("schlafzimmer").child("jalousien").setValue(grad);
            myJalousienControl.setStatus(grad);

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

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

    // Firebase Listener // check for default value
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

    //defaultpopup
    private void showPopup() {
        new AlertDialog.Builder(BedRoomActivity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.defaultwertmsg))
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Uri uri = Uri.parse("http://smart.design-muc.de/admin");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);

                    }
                }).show();
    }




}

