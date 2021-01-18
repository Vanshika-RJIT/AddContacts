package com.example.minorproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_show)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        checkAndroidVersion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();

        } else {
            Snackbar.make(this.findViewById(android.R.id.content),
                    "Your Android Version is not Suitable For this App",
                    Snackbar.LENGTH_INDEFINITE).show();

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_CONTACTS) + ContextCompat
                .checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.READ_CONTACTS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.CALL_PHONE)) {

                Snackbar.make(this.findViewById(android.R.id.content),
                        "Please Grant Permissions to Read Contacts and Make Calls",
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        v -> requestPermissions(
                                new String[]{Manifest.permission
                                        .READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                PERMISSIONS_MULTIPLE_REQUEST)).show();
            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .READ_CONTACTS, Manifest.permission.CALL_PHONE},
                        PERMISSIONS_MULTIPLE_REQUEST);
            }
        } else {
            Toast.makeText(this, "Already Granted Permissions", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean callPhone = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readContacts = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (callPhone && readContacts) {
                        Toast.makeText(this, "Granted Permissions Before", Toast.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(this.findViewById(android.R.id.content),
                                "Please Grant Permissions to Read Contacts and Make Calls",
                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                                v -> requestPermissions(
                                        new String[]{Manifest.permission
                                                .READ_CONTACTS, Manifest.permission.CALL_PHONE},
                                        PERMISSIONS_MULTIPLE_REQUEST)).show();
                    }
                }
                break;
        }
    }
}