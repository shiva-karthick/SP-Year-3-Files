package com.sp.p1617588;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AlarmActivity extends AppCompatActivity {

    public Ringtone ringtoneSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").

        // alarm notification
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringtoneSound.play();
    }

    public void stopAlarm(View view) {
        ringtoneSound.stop();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
        // finishAndRemoveTask();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")

        // stop the ringtone
        ringtoneSound.stop();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();

    }
}
