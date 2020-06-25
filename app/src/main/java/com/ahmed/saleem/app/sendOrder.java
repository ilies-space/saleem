package com.ahmed.saleem.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class sendOrder extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mMapView;
    TextView phone, model, problem, problemDiscription;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_order);

        /* ----------------------- [Start of : components initialisation ] -----------------------*/

        //-------MAP initialising:
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = findViewById(R.id.mapview);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
        //-------MAP END

        phone = findViewById(R.id.phoneTv);
        model = findViewById(R.id.modelTv);
        problem = findViewById(R.id.problemTv);
        problemDiscription = findViewById(R.id.problemDescriptionTv);


        //receiving all data input by the user as a orderModel
        OrderModel orderInput = (OrderModel) getIntent().getSerializableExtra("orderInput");
        assert orderInput != null;
        //show user input data on the screen
        phone.setText("" + orderInput.phone);
        model.setText("" + orderInput.model);
        problem.setText("" + orderInput.problem);
        problemDiscription.setText("" + orderInput.problemDiscription);

        /* ----------------------- [End of : components initialisation ] -----------------------*/



    }




    /* ------------------------ [Start of : Map Listener ] ------------------------*/

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


    // عند الضغط على سهم الرجوع أو التعديل
    public void back(View view) {
        onBackPressed();
    }


    //إرسال الطلب

    public void send(View view) {
        ProgressDialog pd = new ProgressDialog(sendOrder.this);
        pd.setMessage("جاري إرسال المعلوومات ... ");
        pd.show();
    }
}