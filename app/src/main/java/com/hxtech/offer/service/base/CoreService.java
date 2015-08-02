package com.hxtech.offer.service.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.app.Application;
import android.content.Context;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by niejunhong on 15-8-2.
 */
public abstract class CoreService extends SpiceService {

  private Map mExtensionMap;

  public CoreService()
  {
    mExtensionMap = new HashMap();
  }


  public static abstract class Extension
  {

    private final CoreService mService;

    public Context getContext()
    {
      return mService;
    }

    public Extension getExtension(Class class1)
    {
      return mService.getExtension(class1);
    }

    public Map getExtensionMap()
    {
      return mService.getExtensionMap();
    }

    public abstract void onAddRequest(SpiceRequest spicerequest);

    public abstract void onCacheManager(Application application, CacheManager cachemanager)
        throws CacheCreationException;

    public abstract void onCreate();

    public abstract void onDestroy();

    public Extension(CoreService coreservice)
    {
      mService = coreservice;
    }
  }

  @Override
  public CacheManager createCacheManager(Application application) throws CacheCreationException {
    CacheManager cachemanager = new CacheManager();
    for (Iterator iterator = mExtensionMap.values().iterator(); iterator.hasNext(); ((Extension) iterator
        .next()).onCacheManager(application, cachemanager)) {}
    return cachemanager;
  }


  public void addRequest(CachedSpiceRequest cachedspicerequest, Set set)
  {
    SpiceRequest spicerequest = cachedspicerequest.getSpiceRequest();
    for (Iterator iterator = mExtensionMap.values().iterator(); iterator.hasNext(); ((Extension) iterator
        .next()).onAddRequest(spicerequest)) {}
    super.addRequest(cachedspicerequest, set);
  }

  public Extension getExtension(Class class1) {
    Extension extension = (Extension) mExtensionMap.get(class1);
    if (extension == null)
    {
      throw new RuntimeException((new StringBuilder()).append("extension [")
          .append(class1.getSimpleName()).append("] not found").toString());
    } else
    {
      return extension;
    }
  }

  public void addExtension(Extension extension) {
    mExtensionMap.put(extension.getClass(), extension);
  }

  public Map getExtensionMap()
  {
    return mExtensionMap;
  }

  @Override
  public void onCreate() {
    onInitializeExtension();
    for (Iterator iterator = mExtensionMap.values().iterator(); iterator.hasNext(); ((Extension) iterator
        .next()).onCreate()) {}
    super.onCreate();
  }

  @Override
  public void onDestroy() {
    for (Iterator iterator = mExtensionMap.values().iterator(); iterator.hasNext(); ((Extension) iterator
        .next()).onDestroy()) {}
    super.onDestroy();
  }

  public abstract void onInitializeExtension();

}
