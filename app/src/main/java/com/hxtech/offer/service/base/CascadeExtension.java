// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc

package com.hxtech.offer.service.base;

import java.util.Iterator;

import android.app.Application;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.SpiceRequest;

// Referenced classes of package com.rjfittime.app.service.base:
// CoreService

public class CascadeExtension extends CoreService.Extension
{
  public static interface Client
  {

    public abstract void setCascadeExtension(CascadeExtension cascadeextension);
  }


  private CacheManager mCacheManager;

  public CascadeExtension(CoreService coreservice)
  {
    super(coreservice);
  }

  public Object execute(SpiceRequest spicerequest, Object obj, long l)
      throws Exception
  {
    prepare(spicerequest);
    Object obj2 = null;
    if (obj != null)
    {
      obj2 = mCacheManager.loadDataFromCache(spicerequest.getResultType(), obj, l);
    }
    Object obj1 = obj2;
    if (obj2 == null)
    {
      spicerequest = ((SpiceRequest) (spicerequest.loadDataFromNetwork()));
      obj1 = spicerequest;
      if (obj != null)
      {
        obj1 = mCacheManager.saveDataToCacheAndReturnData(spicerequest, obj);
      }
    }
    return obj1;
  }

  public void onAddRequest(SpiceRequest spicerequest)
  {
    if (spicerequest instanceof Client)
    {
      ((Client) spicerequest).setCascadeExtension(this);
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

  public void prepare(SpiceRequest spicerequest)
  {
    for (Iterator iterator = getExtensionMap().values().iterator(); iterator.hasNext(); ((CoreService.Extension) iterator
        .next()).onAddRequest(spicerequest)) {}
  }
}
