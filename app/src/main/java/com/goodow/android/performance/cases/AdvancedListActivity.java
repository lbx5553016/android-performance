package com.goodow.android.performance.cases;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.goodow.android.performance.BuildConfig;

public class AdvancedListActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
  private static final int OVERLAY_PERMISSION_REQ_CODE = 500;
  private ReactRootView mReactRootView;
  private ReactInstanceManager mReactInstanceManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mReactRootView = new ReactRootView(this);
    mReactInstanceManager = ReactInstanceManager.builder()
        .setApplication(getApplication())
        .setBundleAssetName("index.android.bundle")
        .setJSMainModulePath("index")
        .addPackage(new MainReactPackage())
        .setUseDeveloperSupport(BuildConfig.DEBUG)
        .setInitialLifecycleState(LifecycleState.RESUMED)
        .build();
    mReactRootView.startReactApplication(mReactInstanceManager, "ReactNativePerformanceApp", null);

    setContentView(mReactRootView);

    // Configure permissions for development error overlay
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (!Settings.canDrawOverlays(this)) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
      }
    }
  }

  @Override
  public void invokeDefaultOnBackPressed() {
    super.onBackPressed();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.canDrawOverlays(this)) {
          // SYSTEM_ALERT_WINDOW permission not granted...
        }
      }
    }
  }

  @Override
  protected void onPause() {
    super.onPause();

    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostPause(this);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostResume(this, this);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostDestroy(this);
    }
  }

  @Override
  public void onBackPressed() {
    if (mReactInstanceManager != null) {
      mReactInstanceManager.onBackPressed();
    } else {
      super.onBackPressed();
    }
  }
}
