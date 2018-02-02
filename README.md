VrPlayerLib SDK for Android
====================================

VrPlayerLib for Android let you display immersive media content embeded in an Android app.

© VIMERSIV INC. 2018 - All Rights Reserved. SpherePlay is a registered trademark.

Usage
------------------------------------

### Dependencies

You must include the following dependency in your gradle build script

```
com.sphereplay.vrplayerlib:sdk:2.0.0
```

### License key

You must register your license key BEFORE using the library. Otherwise it will not work. To acquire a trial license key, please contact us at info@sphereplay.com.

```
VrPlayerLib.registerLicenseKey("YOUR-LICENSE-KEY");
```

### Player Manipulation

#### Method #1: Stand-alone Activity

This is the easiest. It will launch a simple pre-made activity that we provide in the library.

```
VrPlayerLib.launchActivity(new VrPlayerMedia("YOUR-MEDIA-URL"));
```

#### Method #2: New Instance

You can create a new instance and attach it manually to a any Layout or ViewGroup.

```
VrPlayerView player = new VrPlayerView();
player.loadMedia(new VrPlayerMedia("YOUR-MEDIA-URL"));
setContentView(player)
```

#### Method #3: Activity Layout

Just include the view in a activity layout. Note that we don't have player-specific attributes, yet.

```
<com.sphereplay.vrplayerlib.VrPlayerView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/player" />
```

You can get the player anytime and manipulate it.

```
VrPlayerView player = findViewById(R.id.player);
player.loadMedia(new VrPlayerMedia("YOUR-MEDIA-URL"));

```

### Media configuration

Before loading a media, some of its rendering settings can be adjusted.

```
VrPlayerMedia media = new VrPlayerMedia("YOUR-MEDIA-URL");
media.setProjectionType(VrPlayerProjectionType.SPHERE);
media.setStereoFormatType(StereoFormatType.MONO);
player.loadMedia(media);
```

### Player configuration

You can change settings and state of the player anytime. The config changes must be commited by loading them in the player.

```
VrPlayerConfig config = player.getConfig();
config.setVrMode(true);
config.setBgColor("#cccccc");
player.loadConfig(config);
```

### Reference

_Documentation will be available soon_