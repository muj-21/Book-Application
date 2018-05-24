package com.example.mujta.bookapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePickerActivity extends AppCompatActivity {


    //created 4 variables, 3 for the objects created in the xml layout and PLACE_PICKER_REQUEST which will launch the place picker
    int PLACE_PICKER_REQUEST = 1;
    TextView tvPlaceName;
    TextView tvPlaceAddress;
    TextView tvPlacePhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);

        //the variables created are assigned to the id given in the xml layout
        tvPlaceName = (TextView)findViewById(R.id.tvPlaceName);
        tvPlaceAddress = (TextView)findViewById(R.id.tvPlaceAddress);
        tvPlacePhoneNumber = (TextView)findViewById(R.id.tvPlacePhoneNumber);
    }

    //created a function which searches for places
    public void searchPlacePicker(View view){

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try{
            //launches the place picker if not it will display error suggesting issues with the Google Play Services
            startActivityForResult(builder.build(PlacePickerActivity.this), PLACE_PICKER_REQUEST);
        }catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }catch (GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }

    }
//When user selects an item from the map, it will display the Name, Address and Phone Number to XML layout
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){

                Place place = PlacePicker.getPlace(PlacePickerActivity.this, data);
                tvPlaceName.setText(place.getName());
                tvPlaceAddress.setText(place.getAddress());
                tvPlacePhoneNumber.setText(place.getPhoneNumber());
            }
        }
    }
}
