package com.sp.restaurantlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

// Added on my own, IDE did not generated this; recommended by SO
import org.w3c.dom.Text;

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
    private TabHost host;


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
//        adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, model);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        // Tab1
        TabHost.TabSpec spec = host.newTabSpec("List");
        spec.setContent(R.id.restaurants_tab);
        spec.setIndicator("List");
        host.addTab(spec);

        // Tab2
        spec = host.newTabSpec("Details");
        spec.setContent(R.id.details_tab);
        spec.setIndicator("Details");
        host.addTab(spec);

        host.setCurrentTab(1);

        list.setOnItemClickListener(onListClick);
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

//            Switch to the List tab (0); List Tab is 0 and Details tab is 1
            host.setCurrentTab(0);

        }
    };

    AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Restaurant r = model.get(position);

            restaurantName.setText(r.getName());
            restaurantAddress.setText(r.getAddress());
            restaurantTel.setText(r.getTelephone());

            if (r.getRestaurantType().equals("Chinese")){
                restaurantTypes.check(R.id.chinese);
            }else if (r.getRestaurantType().equals("Western")){
                restaurantTypes.check(R.id.western);
            }else if (r.getRestaurantType().equals("Indian")){
                restaurantTypes.check(R.id.indian);
            }else if (r.getRestaurantType().equals("Indonesia")){
                restaurantTypes.check(R.id.indonesian);
            }else if (r.getRestaurantType().equals("Korean")){
                restaurantTypes.check(R.id.korean);
            }else if (r.getRestaurantType().equals("Japanese")){
                restaurantTypes.check(R.id.japanese);
            }else{
                restaurantTypes.check(R.id.thai);
            }
            host.setCurrentTab(1);
        }
    };

    static class RestaurantHolder{
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row){
            restName = (TextView)row.findViewById(R.id.restName);
            addr = (TextView)row.findViewById(R.id.restAddr);
            icon = (ImageView)row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant r){
            restName.setText(r.getName());
            addr.setText(r.getAddress() + ", " + r.getTelephone());

            if (r.getRestaurantType().equals("Chinese")){
                icon.setImageResource(R.drawable.ball_red);
            }else if (r.getRestaurantType().equals("Western")){
                icon.setImageResource(R.drawable.ball_yellow);
            }else{
                icon.setImageResource(R.drawable.ball_green);
            }
        }

    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
        RestaurantAdapter(){
            super(RestaurantList.this,R.layout.row,model);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            RestaurantHolder holder;
            if (row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            }else{
                holder = (RestaurantHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return (row);
        }
    }
}

