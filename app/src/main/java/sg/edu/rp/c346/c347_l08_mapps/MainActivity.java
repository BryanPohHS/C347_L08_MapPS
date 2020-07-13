package sg.edu.rp.c346.c347_l08_mapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
{
  Button btn1, btn2, btn3;
  private GoogleMap map;
  LatLng poi_hq, poi_central, poi_east;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    poi_hq = new LatLng(1.424450, 103.829849);
    poi_central = new LatLng(51.592770, -0.195460);
    poi_east = new LatLng(53.595428, -1.308810);

    FragmentManager fm = getSupportFragmentManager();
    SupportMapFragment mapFragment = (SupportMapFragment)
            fm.findFragmentById(R.id.map);

    mapFragment.getMapAsync(new OnMapReadyCallback()
    {
      @Override
      public void onMapReady(GoogleMap googleMap)
      {
        map = googleMap;

        // Set default loc when start
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_hq, 11));

        // Set toast on marker click to show toast
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
          @Override
          public boolean onMarkerClick(Marker marker)
          {
            Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
          }
        });

        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
          map.setMyLocationEnabled(true);
        } else {
          Log.e("GMap - Permission", "GPS access has not been granted");
          ActivityCompat.requestPermissions(MainActivity.this,
                  new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        // Map tools
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        Marker hq = map.addMarker(new
                MarkerOptions()
                .position(poi_hq)
                .title("HQ - North")
                .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                        "Tel:65433456")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        Marker central = map.addMarker(new
                MarkerOptions()
                .position(poi_central)
                .title("Central")
                .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                        "Operating hours: 11am-8pm\n" +
                        "Tel:67788652")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));

        Marker east = map.addMarker(new
                MarkerOptions()
                .position(poi_east)
                .title("East")
                .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                        "Operating hours: 9am-5pm\n" +
                        "Tel:66776677")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
      }
    });

    btn1 = findViewById(R.id.btn1);
    btn2 = findViewById(R.id.btn2);
    btn3 = findViewById(R.id.btn3);

    btn1.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if(map != null){
          map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_hq,11));
        }
      }
    });

    btn2.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if(map != null){
          map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central, 11));
        }
      }
    });

    btn3.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        if(map != null){
          map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east, 11));
        }
      }
    });
  }
}