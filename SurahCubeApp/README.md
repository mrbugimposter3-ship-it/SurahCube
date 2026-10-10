# Surah Cube — Android APK

This project wraps the game (`app/src/main/assets/index.html`, a copy of
`surah-cube-game.html`) in a fullscreen Android WebView.

## How to build the APK

The APK could not be pre-compiled on this machine because the Android SDK is
not installed. Building it takes ~5 minutes with Android Studio:

1. Install **Android Studio** (free): https://developer.android.com/studio
   (this installs the Android SDK + Gradle automatically).
2. Open Android Studio → **Open** → select this `SurahCubeApp` folder.
3. Let Gradle sync finish (first sync downloads build tools — internet needed).
4. Menu: **Build → Build App Bundle(s) / APK(s) → Build APK(s)**.
5. Your APK appears at:
   `app/build/outputs/apk/debug/app-debug.apk`
   (click "locate" in the popup Android Studio shows).

Copy the APK to your phone and tap it to install
(allow "install from unknown sources" when prompted).

## Command-line build (this machine is already set up)

Android SDK lives at `C:\Users\rafeh\AndroidSdk`, Gradle 8.9 at
`C:\Users\rafeh\gradle\gradle-8.9`.

```powershell
$env:ANDROID_HOME = "$env:USERPROFILE\AndroidSdk"
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot"
& "$env:USERPROFILE\gradle\gradle-8.9\bin\gradle.bat" -p SurahCubeApp assembleDebug assembleRelease --no-daemon
```

Outputs:
- Debug:   `app/build/outputs/apk/debug/app-debug.apk`
- Release: `app/build/outputs/apk/release/app-release.apk` (signed)

## Signing key

The release APK is signed with `surahcube.keystore` (this folder):
- alias: `surahcube`, password: `surahcube123` (store + key)
- valid ~30 years, self-signed — fine for personal use and sharing.
- **Keep this file safe**: all future updates must be signed with the same key
  or Android will refuse to install them over the old app.
- For Google Play you would switch to Play App Signing later; this key works
  as the upload key.

## Extras included

- App icon (dotted-cube): `res/mipmap-*/ic_launcher.png` + adaptive icon for
  Android 8+; 512px Play-Store version at `play-store-icon.png`.
- Splash screen: `res/drawable/splash_background.xml` shown while the WebView loads.

## Notes

- The **INTERNET** permission is only used to download surah text
  (Arabic / English / Urdu) from api.alquran.cloud the first time each
  surah is opened; it is then cached on-device for offline reading.
- When you update `surah-cube-game.html`, copy it over
  `app/src/main/assets/index.html` and rebuild.
- No-code alternative: upload `surah-cube-game.html` to a static host and run
  it through https://www.pwabuilder.com to generate an installable Android package.
