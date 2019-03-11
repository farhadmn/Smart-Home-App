package de.design_muc.SmartHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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

            Toast.makeText(this, "Abmelden", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.settings) {

            startActivity(new Intent(this, SettingsActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}

