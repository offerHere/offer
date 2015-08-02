package com.hxtech.offer.service.base;

import android.app.Application;
import android.content.Context;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.SpiceRequest;

// Referenced classes of package com.rjfittime.app.service.base:
// CoreService

public class ApplicationContextExtension extends CoreService.Extension
{
  public static interface Client
  {

    public abstract void setContext(Context context);
  }


  public ApplicationContextExtension(CoreService coreservice)
  {
    super(coreservice);
  }

  public void onAddRequest(SpiceRequest spicerequest)
  {
    if (spicerequest instanceof Client)
    {
      ((Client) spicerequest).setContext(getContext().getApplicationContext());
    }
  }

  public void onCacheManager(Application application, CacheManager cachemanager)
      throws CacheCreationException
  {}

  public void onCreate()
  {}

  public void onDestroy()
  {}
}
