package com.android.agliofirebasetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    public MessageAdapter mMessageAdapter;
    public ListView mMessageListView;

    // Firebase instance variables
    private DatabaseReference mDatabase, demoRef;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // [START initialize_database_ref]
        //database reference pointing to root of database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]


        //database reference pointing to demo node
        demoRef = mDatabase.child("demo");


        mMessageListView = (ListView) findViewById(R.id.messageListView);


        // Initialize message ListView and its adapter
        List<Aglio> aglio = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, aglio);
        mMessageListView.setAdapter(mMessageAdapter);


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Aglio aglio = dataSnapshot.getValue(Aglio.class);
                mMessageAdapter.add(aglio);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        demoRef.addChildEventListener(mChildEventListener);
    }
}
