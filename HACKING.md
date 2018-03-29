# Development Environment Setup

## Choice of development tools
This project uses Kotlin as the programming language and Android as the framework.<br/>
The Kotlin programming language is less bug-prone compared to Java, is concise and very approachable for people coming from a Python background. It is officially supported in the Android operating system and framework.<br/>
Android Studio was used at the time of writing this guide because Emacs didn't have Kotlin support.<br/>

## Prerequisites
The only prerequisite for this setup is having JDK 8 or later installed. On Debian, the package name is `openjdk-8-jdk`. <br/>
You don't have to install Kotlin. Let Android Studio determine and fetch the correct Kotlin jar for you.

## Android Studio
1. Download Android Studio from their official website. It will be a .tar.gz file for GNU/Linux systems.<br/>
2. Extract Android Studio from the .tar.gz file and run the executable called studio.sh<br/>
3. Go to File -> Open and point to the location where this repository is cloned. Then proceed to install all the new jars that it will prompt you to install.

## Kotlin Plugin
Android Studio might have prompted you to install Kotlin plugin during project import. Otherwise, go to File -> Settings -> Plugins and search for Kotlin. Install the Kotlin plugin and restart Android Studio.

## Building and running
The development version of the app can be run in two ways - on a physical Android device or on an Android emulator.

### Using emulator
Android Studio will let you download and use emulators. Go to Tools -> Android -> AVD Manager to create a new Android virtual device aka emulator. If the emulator is not automatically selected, do the following:
- Go to Run -> Edit Configurations. 
- In the dialog box that appears, navigate to Android App -> app in the left pane. 
- In the right pane, in the tab called General, you will find "Deployment Target Options". Select emulator and then your device.

### Using Android device over USB
- Get a microUSB to USB cable and connect your Android device to your development machine via its USB port.
- Make sure you have Developer option enabled in your Settings app. Otherwise, go to About Phone in Settings and tap 7 times on the Build number to unlock it.
- Go to Developer options, scroll down to the "Debugging" section and enable "Android Debugging".
- Optionally, you can also turn on the "Stay awake" option to avoid having to repeatedly unlock your phone.
- You Android Studio should automatically recognize your device, otherwise, follow the same steps given above for emulator and select your USB device instead of the emulator.

## Common problems
On GNU/Linux machines, the Android emulator fails to start when there is another virtual machine running. If you FreedomBox VM is running, you might have to shut it down by running the command "vagrant halt" before you can launch the emulator.<br/>
Sometimes a clean and build is required when switching between branches with significant difference. You will find both the options under the Build menu in Android Studio.

## Releasing to app stores 
To make a new release, update the versionCode and versionNumber in app/build.gradle and create a tag like v0.x<br/>

### F-Droid
The F-Droid store automatically picks up new releases on creating a new tag. If any changes are required, make a merge request to their repository called [fdroiddata](https://gitlab.com/fdroid/fdroiddata). The metadata file for FreedomBox is at metadata/org.freedombox.freedombox.txt

### Google Play
Submission to Google Play is manual. Please write to admin@freedombox.org to make a release.<br/>
The following keys are required to make a release to Google Play.
1. username and password for Google Play Developer console
2. key store (say android.jks)
2. Key store password for the above android.jks
3. Key password for FreedomBox

You will need a signed APK to make a release. Go to Build -> Generate Signed APK. You must have a key store, the password to the key store, and a key password for each pair of signing keys. Enter the required details and press 'Next'. Choose v1 (jar signing) in the next dialog box in the wizard.

After generating the signed APK, login to the Google Play Developer console, upload the new APK, write release notes and make a release.
