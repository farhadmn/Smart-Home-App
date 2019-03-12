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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Alarmanlage;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.Bekleidungsvorschlag;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.JalousienSteuerung;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.LichtStatus;
import de.design_muc.SmartHome.SenSoModClasses.Sensoren.WettervorhersageSensor;

public class SchlafzimmerActivity extends BaseActivity {


    Switch SwitchLicht,SwitchAlarm, SwichtOutfit;
    private DatabaseReference myRef, myRef_J;
    private boolean defaultValue;


    private String vorschlag;

    //sensoren

    WettervorhersageSensor myWettervorhersageSensor;

    private LichtStatus myLicht;
    private Alarmanlage myAlarmanlage;
    private JalousienSteuerung myJalousienSteuerung;
    private Bekleidungsvorschlag mybekleidungvorschlag;
    TextView stufe,vorschlagTextView;
    SeekBar seekbarjal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
     //   myRef_J = database.getReference();


        myRef.child("Settings").child("BedRoomDefaulValue").addValueEventListener(myValueEventListener);
        myRef.child("schlafzimmer").child("licht").addValueEventListener(myLichtValueEventListener);
        myRef.child("schlafzimmer").child("jalousien").addValueEventListener(myjalousientValueEventListener);
        myRef.child("Global_values").child("alarmanlage").addValueEventListener(myAlarmValueEventListener);
        myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").addValueEventListener(myOutfitValueEventListener);

        SwitchLicht = (Switch)findViewById(R.id.switch3);
        SwitchAlarm = (Switch)findViewById(R.id.switch6);
        SwichtOutfit = (Switch)findViewById(R.id.switch7);


        seekbarjal = (SeekBar) findViewById(R.id.seekBar6);
        stufe = (TextView) findViewById(R.id.stufen);
        vorschlagTextView = (TextView) findViewById(R.id.textView13);




        vorschlagTextView = (TextView) findViewById(R.id.textView13);

        //initial. Sensoren
        myLicht= new LichtStatus(true);
        myJalousienSteuerung= new JalousienSteuerung();


        myAlarmanlage = Alarmanlage.getInstance();


        mybekleidungvorschlag = Bekleidungsvorschlag.getInstance();

        //Listener  myWettervorhersageSensor.getWetterValueAPI()


        myWettervorhersageSensor = WettervorhersageSensor.getInstance();


        SwitchLicht.setOnCheckedChangeListener(myLichtOnCheckedChangeListener );

        SwitchAlarm.setOnCheckedChangeListener(myAlarmOnCheckedChangeListener);
        SwichtOutfit.setOnCheckedChangeListener(myOutFitOnCheckedChangeListener);

        seekbarjal.setOnSeekBarChangeListener(myJalOnSeekBarChangeListener);






        myJalousienSteuerung.decisionLogic();

        mybekleidungvorschlag.decisionLogic();


        getVorschlag();

        //mybekleidungvorschlag






    }



    public  void getVorschlag() {


        if (mybekleidungvorschlag.getStatus()) {


            vorschlag = mybekleidungvorschlag.getVorschlag();


            if (vorschlag != null) {

                vorschlagTextView.setText(vorschlag);

            } else {
                vorschlagTextView.setText("Outfitvorschlag  nicht möglich.");
            }
        } else {
            vorschlagTextView.setText("Outfitvorschlag einschalten. ");

        }
    }







    // ValueEventListener for Outfit

    ValueEventListener myOutfitValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            Boolean myOutfitStatus = (Boolean) dataSnapshot.getValue();
            mybekleidungvorschlag.setStatus(myOutfitStatus);

            if(mybekleidungvorschlag.getStatus()==false){
                SwichtOutfit.setChecked(false);

            }
            else {
                SwichtOutfit.setChecked(true);

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


            if(isChecked)
            {
                SwichtOutfit.setText("An");
                mybekleidungvorschlag.setStatus(true);
                myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").setValue(true);
                getVorschlag();

            }
            else {
                SwichtOutfit.setText("Aus");
                mybekleidungvorschlag.setStatus(false);
                myRef.child("schlafzimmer").child("schlafzimmer_bekleidung").setValue(false);
                vorschlagTextView.setText("");

            }
        }
    };





    // ValueEventListener for mytv

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





    // oncheckChangelistener  Alarmanlage


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


    // mylicht

    ValueEventListener myLichtValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            Boolean myllchtStatus = (Boolean) dataSnapshot.getValue();
            myLicht.setStatus(myllchtStatus);

            if(myLicht.getStatus()==false){
                SwitchLicht.setChecked(false);

            }
            else {
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


            if(isChecked)
            {
                SwitchLicht.setText("An");
                myLicht.setStatus(true);
                myRef.child("schlafzimmer").child("licht").setValue(true);

            }
            else {
                SwitchLicht.setText("Aus");
                myLicht.setStatus(false);
                myRef.child("schlafzimmer").child("licht").setValue(false);

            }
        }
    };




    // ValueEventListener for myjalousien

    ValueEventListener myjalousientValueEventListener = new ValueEventListener(){
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {




            //con string to int
          //

            String sDB=  String.valueOf(dataSnapshot.getValue());

            int statusBD=Integer.valueOf(sDB);


          // Toast.makeText(getApplicationContext(), "You pressed Test." +sDB, Toast.LENGTH_LONG).show();



            if( statusBD==3){
                seekbarjal.setProgress(3);

            }

            if( statusBD==2){
                seekbarjal.setProgress(2);

            }

            if( statusBD==1){
                seekbarjal.setProgress(1);

            }

            if( statusBD==0){
                 seekbarjal.setProgress(0);

            }

            myJalousienSteuerung.setStatus(statusBD);

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
            stufe.setText(gradStrings);



           myRef.child("schlafzimmer").child("jalousien").setValue(grad);
           myJalousienSteuerung.setStatus(grad);

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
        new AlertDialog.Builder(SchlafzimmerActivity.this)
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

