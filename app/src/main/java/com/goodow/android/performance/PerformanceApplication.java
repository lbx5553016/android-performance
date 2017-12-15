package com.goodow.android.performance;

import android.app.Application;

import com.goodow.android.performance.metrics.PerformanceContentProvider;

/**
 * Created by larry on 2017/12/15.
 */

public class PerformanceApplication extends Application {
  public PerformanceApplication() {
    super();
    PerformanceContentProvider.init(this);
  }
}
