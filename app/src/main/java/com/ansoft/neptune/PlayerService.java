package com.ansoft.neptune;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class PlayerService extends Service {
    private static final String TAG = null;
    public static MediaPlayer mp;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {

        mp = new MediaPlayer();
        try {
            if (intent!=null) {
                String link = intent.getStringExtra("link");
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setDataSource(link);
                mp.prepare();
                mp.start();
            }
        }catch (IOException e){

        }
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
    }

    @Override
    public void onLowMemory() {

    }
}
