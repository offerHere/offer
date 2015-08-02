package com.hxtech.offer.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.hxtech.offer.app.ApplicationComponentContext;
import com.hxtech.offer.service.OfferService;
import com.hxtech.offer.utils.LogUtils;
import com.octo.android.robospice.SpiceManager;

/**
 * Created by haoozhou on 2015/7/4.
 */
public class BaseActivity extends ActionBarActivity {
  private String TAG = getClass().getSimpleName();

  public static final String SPICE_MANAGER_SERVER = OfferService.class.getCanonicalName();

  private final ApplicationComponentContext mApplicationComponentContext =
      new ApplicationComponentContext();


  protected void debug(String msg) {
    LogUtils.debug(TAG, msg);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getSupportActionBar() != null) {
      ActionBar ab = getSupportActionBar();
      ab.setDisplayHomeAsUpEnabled(true);
    }
    mApplicationComponentContext.addApplicationService(SPICE_MANAGER_SERVER,
        new ApplicationComponentContext.SpiceServiceProxy(OfferService.class));
    mApplicationComponentContext.create(this);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected ApplicationComponentContext getApplicationComponentContext()
  {
    return mApplicationComponentContext;
  }


  protected SpiceManager getServiceManager()
  {
    return (SpiceManager) getApplicationComponentContext().getApplicationService(
        SPICE_MANAGER_SERVER);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mApplicationComponentContext.destroy();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mApplicationComponentContext.stop();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mApplicationComponentContext.start(this);
  }
}
