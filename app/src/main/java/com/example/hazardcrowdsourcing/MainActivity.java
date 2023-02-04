package com.example.hazardcrowdsourcing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setBackground(null);

       bottomNavigationView.getMenu().getItem(1).setEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new NewsFragment()).commit();

        NewsFragment newsFragment = new NewsFragment();
        ProfileFragment profileFragment  = new ProfileFragment();
        //NewsFragment newsFragment = new NewsFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch (item.getItemId()) {
                    case R.id.miHome:
                        // temp = new NewsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, newsFragment).commit();
                        return true;
                    // break;
                    case R.id.bottommap:
                        temp = new ProfileFragment();
                        Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                        return true;
                       /* Intent i = new Intent(MainActivity.this , MapsActivity.class);
                        startActivity(i);*/
                        //break;
                    case R.id.miProfile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, profileFragment).commit();
                        return true;
                    //break;
                }
                return false;
            }
        });


    }


    public void showDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Copyright");
       alertDialog.setMessage("© 2023 Hazard Crowdsourcing, Inc. All rights reserved. ");
       //  Alert dialog button
       alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
               new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       //  Alert dialog action goes here
                        // onClick button code here
                        dialog.dismiss();// use dismiss to cancel alert dialog
                   }
                });
        alertDialog.show();
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.copyright, menu);
       return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {

           case R.id.copy:
               showDialog();
               return true;

           default:
               return super.onOptionsItemSelected(item);
       }
    }


}

