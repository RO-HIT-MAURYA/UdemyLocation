package apollo.hospitals.udemylocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    long minTime = 50;
    float minDist = 100;
    LocationManager locationManager;
    LocationListener locationListener;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //String[] strings = {LOCATION_SERVICE};

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
    }


    private String round(Double location) {
        return String.format("%.2f", location);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location)
            {
                textView.setText("latIs:=>" + round(location.getLatitude()) + "\n" + "lotIs:=>" + round(location.getLongitude()));
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDist, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDist, locationListener);
        locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, minTime, minDist, locationListener);
    }
}
