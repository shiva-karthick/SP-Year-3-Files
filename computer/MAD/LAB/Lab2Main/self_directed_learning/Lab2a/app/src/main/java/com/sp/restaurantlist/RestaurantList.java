package com.sp.restaurantlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

// Added on my own, IDE did not generated this recommended by SO
import java.util.ArrayList;
import java.util.List;


public class RestaurantList extends AppCompatActivity {
    // declaration of variables
    private EditText restaurantName;
    private EditText restaurantAddress;
    private EditText restaurantTel;
    private RadioGroup restaurantTypes;
    private Button buttonSave;

    private List<Restaurant> model = new ArrayList<Restaurant>();
    //    private ArrayAdapter<Restaurant> adapter = null;
    private RestaurantAdapter adapter = null;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        restaurantName = (EditText) findViewById(R.id.restaurant_name);
        restaurantAddress = (EditText) findViewById(R.id.restaurant_address);
        restaurantTel = (EditText) findViewById(R.id.restaurant_tel);
        restaurantTypes = (RadioGroup) findViewById(R.id.restaurant_types);

        buttonSave = (Button) findViewById(R.id.button_save);
        // listen for button events for onSave function
        buttonSave.setOnClickListener(onSave);

        list = (ListView) findViewById(R.id.restaurants);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // To read data from restaurantName EditText
            String nameStr = restaurantName.getText().toString();

            // To read data from restaurantAddress EditText
            String addrStr = restaurantAddress.getText().toString();

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
//            String combineStr = nameStr + "\n" + addrStr + "\n" + telStr + "\n" + restType;
//            Toast.makeText(v.getContext(),combineStr,Toast.LENGTH_LONG).show();

//            Create Restaurant Data model
            Restaurant restaurant = new Restaurant();

//            Update the restaurant Data model
            restaurant.setName(nameStr);
            restaurant.setAddress(addrStr);
            restaurant.setTelephone(telStr);
            restaurant.setRestaurantType(restType);

//            Pass the record to ArrayAdapter
//            It will update the ListArray and the ListView
            adapter.add(restaurant);

        }
    };

    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = (TextView) row.findViewById(R.id.restName);
            addr = (TextView) row.findViewById(R.id.restAddr);
            icon = (ImageView) row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r) {
            restName.setText(r.getName());
            addr.setText(r.getAddress());

            //Get different icon if the restaurant’s type is different
            if (r.getRestaurantType().equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (r.getRestaurantType().equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() {
            super(RestaurantList.this, R.layout.row, model);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RestaurantHolder holder;
            if (row == null) { // -> No recycled row
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
                /* This “View Holder” is attached to the row via
                    setTag() method. If row is recycled, the
                    “View Holder” can be called via getTag()
                    method */
            } else { // -> Recycled row available
                holder = (RestaurantHolder) row.getTag();
            }
            holder.populateFrom(model.get(position));
            return (row);
        }
    }
}

