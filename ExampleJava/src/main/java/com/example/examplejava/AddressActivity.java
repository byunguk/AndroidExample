package com.example.examplejava;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AddressActivity extends AppCompatActivity {
    private static final String API_KEY = "";
    private static final String TAG = "AppCompatActivity";

    private String streetNumber;
    private String route;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), API_KEY);
        }

        setUi();
    }

    private void setUi() {
        AutocompleteSupportFragment autocompleteFragment =
                (AutocompleteSupportFragment)getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS_COMPONENTS));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                ((EditText)findViewById(R.id.street_edit_text)).setText(place.getAddress());
                for (AddressComponent component : place.getAddressComponents().asList()) {
                    Log.i(TAG, "type: " + component.getTypes());
                    if (component.getTypes().contains("street_number")) {
                        streetNumber = component.getName();
                    } else if (component.getTypes().contains("route")) {
                        route = component.getName();
                    } else if (component.getTypes().contains("locality")) {
                        ((EditText)findViewById(R.id.city_edit_text)).setText(component.getName());
                    } else if (component.getTypes().contains("postal_code")) {
                        ((EditText)findViewById(R.id.zip_code_edit_text)).setText(component.getName());
                    }
                }
                ((EditText)findViewById(R.id.street_edit_text)).setText(streetNumber + " " + route);
            }

            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG, "An error occurred: $status");
            }
        });
    }
}
