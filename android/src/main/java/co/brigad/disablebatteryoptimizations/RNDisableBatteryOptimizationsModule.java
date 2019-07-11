package co.brigad.disablebatteryoptimizations;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.module.annotations.ReactModule;

import android.content.Intent;
import android.provider.Settings;
import android.os.PowerManager;
import android.net.Uri;
import android.os.Build;

@ReactModule(name = RNDisableBatteryOptimizationsModule.MODULE_NAME)
public class RNDisableBatteryOptimizationsModule extends ReactContextBaseJavaModule {

  public static final String MODULE_NAME = "RNDisableBatteryOptimizations";
  private final ReactApplicationContext reactContext;

  public RNDisableBatteryOptimizationsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void openBatteryModal() {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      Intent intent = new Intent();
      intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      reactContext.startActivity(intent);
    }
  }

  @ReactMethod
  public void isBatteryOptimizationEnabled(Promise promise) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        String packageName = reactContext.getPackageName();
        PowerManager pm = (PowerManager) reactContext.getSystemService(reactContext.POWER_SERVICE);
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
          promise.resolve(true);
          return ;
        }
    }
    promise.resolve(false);
  }

  @Override
  public String getName() {
    return MODULE_NAME;
  }
}