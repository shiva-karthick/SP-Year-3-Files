package com.sp.p1617588;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetailForm extends AppCompatActivity {

    // declaration of variables
    private EditText restaurantName;
    private EditText restaurantAddress;
    private EditText restaurantTel;
    private RadioGroup restaurantTypes;
    private Button buttonSave;

    private RestaurantHelper helper = null;

    // need to store the incoming restaurantID from RestaurantList.java
    private String restaurantID = "";

    private TextView location = null;
    private GPSTracker gpsTracker;
    private double latitude = 0.0d;
    private double longitude = 0.0d;
    private double myLatitude = 0.0d;
    private double myLongitude = 0.0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_form);

        restaurantName = (EditText) findViewById(R.id.restaurant_name);
        restaurantAddress = (EditText) findViewById(R.id.restaurant_address);
        restaurantTel = (EditText) findViewById(R.id.restaurant_tel);
        restaurantTypes = (RadioGroup) findViewById(R.id.restaurant_types);

        buttonSave = (Button) findViewById(R.id.button_save);
        // listen for button events for onSave function
        buttonSave.setOnClickListener(onSave);
        helper = new RestaurantHelper(this);

        location = (TextView) findViewById(R.id.location);
        gpsTracker = new GPSTracker(DetailForm.this);

        restaurantID = getIntent().getStringExtra("ID");
        if (restaurantID != null) {
            load();
        }
    }

    // our own function load(): loads the details into the detail_form.java ;
    private void load() {
        Cursor c = helper.getById(restaurantID);
        c.moveToFirst();
        restaurantName.setText(helper.getRestaurantName(c));
        restaurantAddress.setText(helper.getRestaurantAddress(c));
        restaurantTel.setText(helper.getRestaurantTel(c));

        if (helper.getRestaurantType(c).equals("Chinese")) {
            restaurantTypes.check(R.id.chinese);
        } else if (helper.getRestaurantType(c).equals("Western")) {
            restaurantTypes.check(R.id.western);
        } else if (helper.getRestaurantType(c).equals("Indian")) {
            restaurantTypes.check(R.id.indian);
        } else if (helper.getRestaurantType(c).equals("Indonesian")) {
            restaurantTypes.check(R.id.indonesian);
        } else if (helper.getRestaurantType(c).equals("Korean")) {
            restaurantTypes.check(R.id.korean);
        } else if (helper.getRestaurantType(c).equals("Japanese")) {
            restaurantTypes.check(R.id.japanese);
        } else {
            restaurantTypes.check(R.id.thai);
        }

        latitude = helper.getLatitude(c);
        longitude = helper.getLongitude(c);
        location.setText(String.valueOf(latitude) + ", " + String.valueOf(longitude));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
        gpsTracker.stopUsingGPS();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.details_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.get_location) {
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
                location.setText(String.valueOf(latitude) + ", " +
                        String.valueOf(longitude));

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            }
            return (true);
        } else if (item.getItemId() == R.id.show_map) {

            // Get my current location
            myLatitude = gpsTracker.getLatitude();
            myLongitude = gpsTracker.getLongitude();

            Intent intent = new Intent(this, RestaurantMap.class);
            intent.putExtra("LATITUDE", latitude);
            intent.putExtra("LONGITUDE", longitude);
            intent.putExtra("MYLATITUDE", myLatitude);
            intent.putExtra("MYLONGITUDE", myLongitude);
            intent.putExtra("NAME", restaurantName.getText().toString());
            startActivity(intent);
            return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // To read data from restaurantName EditText
            String nameStr = restaurantName.getText().toString();

            // To read data from restaurantAddress EditText
            String addressStr = restaurantAddress.getText().toString();

            // To read data from restaurantTel EditText
            String telStr = restaurantTel.getText().toString();

            String restType = "";

            // To read selection of restaurantTypes radioGroup
            switch (restaurantTypes.getCheckedRadioButtonId()) {
                case R.id.chinese:
                    restType = "Chinese";
                    break;
                case R.id.western:
                    restType = "Western";
                    break;
                case R.id.indian:
                    restType = "Indian";
                    break;
                case R.id.indonesian:
                    restType = "Indonesian";
                    break;
                case R.id.korean:
                    restType = "Korean";
                    break;
                case R.id.japanese:
                    restType = "Japanese";
                    break;
                case R.id.thai:
                    restType = "Thai";
                    break;
            }
            if (restaurantID == null) {
                // Create Restaurant Data Model
                helper.insert(nameStr, addressStr, telStr, restType, latitude, longitude);
            } else {
                helper.update(restaurantID, nameStr, addressStr, telStr, restType, latitude, longitude);
            }
            // To close current Activity class and exit
            finish();
        }
    };
}
