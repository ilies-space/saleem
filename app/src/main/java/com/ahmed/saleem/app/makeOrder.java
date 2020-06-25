package com.ahmed.saleem.app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.ahmed.saleem.app.ui.home.HomeFragment.pd2;


public class makeOrder extends AppCompatActivity implements OnMapReadyCallback {
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private MapView mMapView;
    //To store user Input DAta
    public String phone, model, prolem, problemDiscription;
    EditText problemDescriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        /* ----------------------- [Start of : components initialisation ] -----------------------*/

        //EditText ( Problem Description )
        problemDescriptionInput = findViewById(R.id.editText);
        //create a list of items for the spinner.
        final String[] items0, items1, items2;
        ArrayAdapter < String > adapter0, adapter1, adapter2;

        //Spinners
        //نوع الجوال
        Spinner dropdown0 = findViewById(R.id.spinner0);
        items0 = new String[] {
                "تحديد نوع الجوال",
                "ايفون ",
                "سامسونغ",
                "اخرى ... "
        };
        adapter0 = new ArrayAdapter < > (getApplicationContext(), R.layout.spinner_item, items0);
        adapter0.setDropDownViewResource(R.layout.spinner_item_dropdown);
        dropdown0.setAdapter(adapter0);


        //نوع الموديل
        Spinner dropdown1 = findViewById(R.id.spinner1);
        items1 = new String[] {
                "تحديد الموديل",
                "ايفون اكس",
                "ايفون 8",
                "اخرى ... "
        };
        adapter1 = new ArrayAdapter < > (getApplicationContext(), R.layout.spinner_item, items1);
        adapter1.setDropDownViewResource(R.layout.spinner_item_dropdown);
        dropdown1.setAdapter(adapter1);


        // نوع المشكلة
        Spinner dropdown2 = findViewById(R.id.spinner2);
        items2 = new String[] {
                "تحديد نوع المشكلة",
                "الشاشة لاتعمل",
                "الصوت",
                "اخرى ..."
        };
        adapter2 = new ArrayAdapter < > (getApplicationContext(), R.layout.spinner_item, items2);
        adapter2.setDropDownViewResource(R.layout.spinner_item_dropdown);
        dropdown2.setAdapter(adapter2);


        // Map view
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = findViewById(R.id.mapview);
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);



        /* ------------------------ [End of : components initialisation ] ------------------------*/



        /*[Start of : spinner listener + setting selected values to ] -----------------------*/

        //نوع الجوال


        dropdown0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView < ? > parent, View view, int position, long id) {
                phone = items0[position];
            }

            @Override
            public void onNothingSelected(AdapterView < ? > parent) {

            }
        });
        //نوع الموديل

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView < ? > parent, View view, int position, long id) {

                model = items1[position];
            }

            @Override
            public void onNothingSelected(AdapterView < ? > parent) {

            }
        });
        // نوع المشكلة

        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView < ? > parent, View view, int position, long id) {

                prolem = items2[position];

            }

            @Override
            public void onNothingSelected(AdapterView < ? > parent) {

            }
        });


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



    /* ------------------------ [Start of : Button listener + input validation ] ------------------------*/


    public void makeanOrder(View view) {

        //check Data :
        if (checkData()) {
            //store selected data to an object to send it to the sendOrder Activity
            OrderModel orderModel = new OrderModel(phone, model, prolem, problemDiscription);
            SecondActivity(orderModel);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("الرجاء التحقق من المعلومات المدخلة و إعادة المحاولة !")

                    .setNegativeButton("حسنا", null).show();

        }



    }

    public boolean checkData() {


        problemDiscription = problemDescriptionInput.getText().toString();
        if (problemDiscription.isEmpty())
            return false;

        if (phone.equals("تحديد نوع الجوال"))
            return false;
        if (model.equals("تحديد الموديل"))
            return false;
        if (prolem.equals("تحديد نوع المشكلة"))
            return false;


        return true;
    }

    public void SecondActivity(OrderModel orderModel) {

        Intent myIntent = new Intent(makeOrder.this, sendOrder.class);
        myIntent.putExtra("orderInput", orderModel);
        makeOrder.this.startActivity(myIntent);


    }

    @Override
    public void onBackPressed() {
        pd2.dismiss();

        super.onBackPressed();
    }
}