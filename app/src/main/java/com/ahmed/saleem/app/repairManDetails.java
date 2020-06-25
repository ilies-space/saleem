package com.ahmed.saleem.app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.ahmed.saleem.app.repairMan.pd;


public class repairManDetails extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mMapView;
    ImageView Like ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_man_details);

        Like = findViewById(R.id.fav);

        //receiving all data input by the user as a orderModel
        RepairmanModel repairmanModel = (RepairmanModel) getIntent().getSerializableExtra("repairmanModel");
        assert repairmanModel != null;

        TextView toolbareName,jobtitle,ratiing,order,name;
        toolbareName = findViewById(R.id.repairmanName);
        jobtitle = findViewById(R.id.tvJobtitne);
        ratiing = findViewById(R.id.tvRating);
        order = findViewById(R.id.tvOrder);
        name = findViewById(R.id.tvname);

        toolbareName.setText(""+repairmanModel.getName());
        jobtitle.setText(""+repairmanModel.getJobTitle());
        ratiing.setText(""+repairmanModel.getEvaluation());
        name.setText(""+repairmanModel.getName());
        order.setText("34"+" طلب ");

        // Map view
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = findViewById(R.id.mapview);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);




    }

    public void back(View view) {

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        pd.dismiss();

        super.onBackPressed();
    }

    public void like(View view) {

        Like.setImageResource(R.drawable.heart);

    }

    public void order(View view) {
        ProgressDialog pd = new ProgressDialog(repairManDetails.this);
        pd.setMessage("جاري طلب الخدمة ...");
        pd.show();
    }
    /* ----------------------- [Start of : Map Listener] -----------------------*/
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //marker to map :
        map.addMarker(new MarkerOptions().position(new LatLng(26.313590, 50.206000)).title("مثال عن مكان"));
        //move Camera to the zone :
        float zoomLevel = 15.0f; //This goes up to 21
        LatLng latLng = new LatLng(26.313590, 50.206000);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /* ------------------------ [End of : Map Listener ] ------------------------*/

}
