// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc

package com.hxtech.offer.service.base;

import android.app.Application;

import com.hxtech.offer.service.io.CacheManagerInterface;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.SpiceRequest;

// Referenced classes of package com.rjfittime.app.service.base:
// CoreService

public class CacheManagerExtension extends CoreService.Extension
{
  public static interface Client
      extends CacheManagerInterface
  {}


  private CacheManager mCacheManager;

  public CacheManagerExtension(CoreService coreservice)
  {
    super(coreservice);
  }

  public void onAddRequest(SpiceRequest spicerequest)
  {
    if (spicerequest instanceof CacheManagerInterface)
    {
      ((CacheManagerInterface) spicerequest).setCacheManager(mCacheManager);
    }
  }

  public void onCacheManager(Application application, CacheManager cachemanager)
      throws CacheCreationException
  {
    mCacheManager = cachemanager;
  }

  public void onCreate()
  {}

  public void onDestroy()
  {}
}
