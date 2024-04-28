package com.practice.gpscoordinates;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnShowCoordinates;
    TextView txtViewCoordinates;

    LocationManager locationManager;

    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // typecasting the widgets
        txtViewCoordinates = (TextView) findViewById(R.id.txt_view_coordinates);
        btnShowCoordinates = (Button) findViewById(R.id.btn_display_coordinates);

        // use the getSystemService() method to get the LOCATION_SERVICE
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // we have to append the coordinates every time instead of setting the text content
                txtViewCoordinates.append(location.getLongitude() + " " + location.getLatitude());
            }
        };

        btnShowCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if the build version of the app is Android R
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
                }
            }
        });

    }
}