package com.android.agliofirebasetest;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 100;
    ImageView image;

    private EditText editText1, editText2;
    private Button submit;
    private DatabaseReference mDatabase, demoRef;
    public String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.display_image);

        editText1 = (EditText) findViewById(R.id.name);
        editText2 = (EditText) findViewById(R.id.description);
        submit = (Button) findViewById(R.id.btnSubmit);

        // [START initialize_database_ref]
        //database reference pointing to root of database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]


        //database reference pointing to demo node
        demoRef = mDatabase.child("demo");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String value1 = editText1.getText().toString();
//                String value2 = editText2.getText().toString();


                // for photo
                Bitmap bm = BitmapFactory.decodeFile(imagePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] imageBytes  = baos.toByteArray();

                // test the below code -> it works uruG Thanks
                String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                Aglio aglio = new Aglio(editText1.getText().toString(),editText2.getText().toString(),imageString);

                // Remember push creates a unique id in database
                demoRef.push().setValue(aglio);
                // demoRef.setValue(value1 + "," + value2 + "," + b);
            }
        });

    }

    public void saveImage(View view) {
        Intent intent = new Intent(MainActivity.this, camera.class);
        startActivity(intent);
    }

    public void openImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);

                    // adding save me

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getApplicationContext(), yourSelectedImage);

                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File(getRealPathFromURI(tempUri));

                    imagePath = finalFile.getAbsolutePath();
                    // Log.d("MainActivity","shankar" + finalFile.getAbsolutePath());
                    // help me

                    image.setImageURI(selectedImage);// To display selected image in image view
                }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void openMainScreen(View view) {
        Intent intent = new Intent(MainActivity.this, MainScreen.class);
        startActivity(intent);
    }
}
