package com.example.hazardcrowdsourcing;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hazardcrowdsourcing.databinding.ActivityMapsBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Vector;

public class  MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerlocation;

    Vector<MarkerOptions> markerOptions;

    private String URL="https://hazardcrowdsourcing.000webhostapp.com/all.php";
    RequestQueue requestque;
    Gson gson;
    Information[] informations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gson= new GsonBuilder().create();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.0,101);

        markerOptions = new Vector<>();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        for(MarkerOptions mark: markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,6));
        sendRequest();
    }


    private void enableMyLocation() {

        String perms[] = {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_NETWORK_STATE"};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                Log.d("Maps","permission granted");
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission

            Log.d("Maps","permission denied");
            ActivityCompat.requestPermissions(this,perms ,200);

        }

    }

    public void sendRequest(){
        requestque = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,onSuccess,onError);
        requestque.add(stringRequest);
    }

    public Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            informations = gson.fromJson(response,Information[].class);

            Log.d("Maklumat","Number of Information Data Point :" + informations.length);

            if (informations.length < 1){
                Toast.makeText(getApplicationContext(),"Problem retrieving ",Toast.LENGTH_LONG).show();
                return;
            }

            for (Information info: informations) {
                Double lat = Double.parseDouble(info.latitude);
                Double lng = Double.parseDouble(info.longitude);
                String title = "Hazard : "+ info.typeHazard;
                 String time= "Time :"+info.newsTime;
               String date = "Date :"+info.newsDate;
                String snippet1= "Reported by : "+ info.reporterName;

                MarkerOptions marker = new MarkerOptions().title(title)
                        .position(new LatLng(lat,lng))
                        .snippet(snippet1+"  "+time+"  "+date)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                mMap.addMarker(marker);

            }

        }
    };
    public Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
}