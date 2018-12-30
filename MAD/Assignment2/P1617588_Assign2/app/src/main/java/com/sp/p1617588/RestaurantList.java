package com.sp.p1617588;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RestaurantList extends AppCompatActivity {

    private Cursor model = null;
    private RestaurantAdapter adapter = null;
    private ListView list;
    private RestaurantHelper helper = null;
    private TextView empty = null;
    private SharedPreferences prefs = null;

    private boolean showMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        empty = (TextView) findViewById(R.id.empty);
        helper = new RestaurantHelper(this);
        list = (ListView) findViewById(R.id.list);
        adapter = new RestaurantAdapter(this, model,0);
        list.setOnItemClickListener(onListClick);
        list.setAdapter(adapter);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    private void initList(){
        if (model != null){
            model.close();
        }
        model = helper.getAll(prefs.getString("sort_order", "restaurantName"));
        adapter.swapCursor(model);
    }

    private SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("sort_order")){
                initList();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        initList();
        if (model.getCount() > 0){
            empty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.add):
                Intent intent;
                intent = new Intent(RestaurantList.this, DetailForm.class);
                startActivity(intent);
                break;
            case (R.id.prefs):
                startActivity(new Intent(this, EditPreferences.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            model.moveToPosition(position);
            String recordID = helper.getID(model);
            Intent intent;
            intent = new Intent(RestaurantList.this, DetailForm.class);
            intent.putExtra("ID", recordID);
            // To start an activity, use the method startActivity(intent) - http://www.vogella.com
            startActivity(intent);
        }
    };

    class RestaurantAdapter extends CursorAdapter {
        RestaurantAdapter(RestaurantList restaurantList, Cursor c, int i) {
            super(RestaurantList.this, c);
        }

        @Override
        public void bindView(View row, Context ctxt, Cursor c) {
            RestaurantHolder holder = (RestaurantHolder) row.getTag();

            holder.populateFrom(c, helper);
        }

        @Override
        public View newView(Context ctxt, Cursor c, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            RestaurantHolder holder = new RestaurantHolder(row);
            row.setTag(holder);
            return (row);
        }
    }

    static class RestaurantHolder {
        private TextView restName = null;
        private TextView addr = null;
        private ImageView icon = null;

        RestaurantHolder(View row) {
            restName = (TextView) row.findViewById(R.id.restName);
            addr = (TextView) row.findViewById(R.id.restAddr);
            icon = (ImageView) row.findViewById(R.id.icon);
        }

        void populateFrom(Cursor c, RestaurantHelper helper) {
            restName.setText(helper.getRestaurantName(c));
            String temp = helper.getRestaurantAddress(c) + ", " + helper.getRestaurantTel(c);
            addr.setText(temp);

            if (helper.getRestaurantType(c).equals("Chinese")) {
                icon.setImageResource(R.drawable.ball_red);
            } else if (helper.getRestaurantType(c).equals("Western")) {
                icon.setImageResource(R.drawable.ball_yellow);
            } else {
                icon.setImageResource(R.drawable.ball_green);
            }
        }
    }
}

