# kotlinUtils

Frequently used utils in kotlin.

# Import library
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
implementation 'com.github.cxyzy1:kotlinUtils:1.0.9'
```

# Feature list and detailed usage
1. ToastExt
   Toast utils to simplify showing toast.
   eg.
   ```
   toast("hello")
   ```
2. IntentExt
   Intent utils, simplify activity transitions.
   eg.
   ```
   startActivity<TestActivity>()
   ```
3. LogUtils
   Utils to simplify android log.
   eg.
   ```
   verbose("test")
   debug("test")
   info("test")
   warn("test")
   try {
       throw Exception("test")
   } catch (e: Exception) {
       error(e)
   }
   ```
4. EncryptUtils
   support encryption and decryption by aes and so on.
5. SizeUtils
   Size convert utils such as px and dp.
6. PathUtils
   Path related utils such as get external sd card path.
7. DeviceUtils
   Utils to get information about device manufacturer and so on.
