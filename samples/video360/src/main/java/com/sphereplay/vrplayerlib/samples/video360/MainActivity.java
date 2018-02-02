package com.sphereplay.vrplayerlib.samples.video360;

import android.app.Activity;
import android.os.Bundle;

import com.sphereplay.vrplayerlib.VrPlayerConfig;
import com.sphereplay.vrplayerlib.VrPlayerLib;
import com.sphereplay.vrplayerlib.VrPlayerMedia;
import com.sphereplay.vrplayerlib.VrPlayerProjectionType;
import com.sphereplay.vrplayerlib.VrPlayerStereoFormat;
import com.sphereplay.vrplayerlib.VrPlayerView;

public class MainActivity extends Activity {

    private enum VrPlayerUsageMethod {
        STANDALONE_ACTIVITY,
        NEW_INSTANCE,
        ACTIVITY_LAYOUT;
    }

    VrPlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // First things first, register you license
        // For a trial license key, please contact us info@sphereplay.com
        // NOTE: To use you own credentials, please see the `build.gradle` script
        VrPlayerLib.registerLicenseKey(this, BuildConfig.SPHEREPLAY_CUSTOMER_ID, BuildConfig.SPHEREPLAY_LICENSE_KEY);

        // Hide the android system bars
        // Required for a better fullscreen experience on certain devices with on-screen buttons
        VrPlayerLib.hideSystemUI(this);

        // Change player configuration
        VrPlayerConfig config = new VrPlayerConfig();
        //config.setBgColor("#999999");
        //config.setBgImageUrl("https://farm5.staticflickr.com/4709/39932776151_a54833cf44_o_d.jpg");
        //config.setVrMode(true);
        //config.setVrModeTransition(false);
        config.setAutoPlay(true);

        // Set up a media to play
        VrPlayerMedia media = new VrPlayerMedia("https://bitmovin-a.akamaihd.net/content/playhouse-vr/m3u8s/105560.m3u8");
        media.setProjectionType(VrPlayerProjectionType.SPHERE);
        media.setStereoFormat(VrPlayerStereoFormat.MONO);

        // The 3 usage methods are showcased below, but they all produce the same result
        // You can change it to test them all if you wish
        switch (VrPlayerUsageMethod.NEW_INSTANCE) {

            // Method #1
            // Start a new activity using the one we provide
            case STANDALONE_ACTIVITY:
                VrPlayerLib.launchActivity(this, config, media);
                return; // End of the line, we are changing activity

            // Method #2
            // Create a new instance and manually attach it to a Layout or ViewGroup
            case NEW_INSTANCE:
                player = new VrPlayerView(this);
                setContentView(player);
                break;

            // Method #3
            // Use the layout xml to create an instance and get a reference using its ID
            case ACTIVITY_LAYOUT:
                setContentView(R.layout.activity_main);
                player = findViewById(R.id.player);
                break;
        }

        // Commit the modified config and load media
        player.loadConfig(config);
        player.loadMedia(media);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Again, making ensuring fullscreen on all devices
        VrPlayerLib.hideSystemUI(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.suspend();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
           player.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }

}
