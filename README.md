# kotlinUtils

Frequently used utils in kotlin.

# Usage
1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency
```
implementation 'com.github.cxyzy1:kotlinUtils:1.0.7'
```

# Feature list
1. ToastExt
   Toast utils to simplify showing toast.
2. IntentExt
   Intent utils, simplify activity transitions.
3. EncryptUtils
   support encryption and decryption by aes and so on.
4. SizeUtils
   Size convert utils such as px and dp.
5. PathUtils
   Path related utils such as get external sd card path.
6. DeviceUtils
   Utils to get information about device manufacturer and so on.
7. LogUtils
   Utils to simplify android log.