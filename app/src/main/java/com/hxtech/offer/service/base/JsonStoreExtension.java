// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc

package com.hxtech.offer.service.base;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hxtech.offer.service.misc.PersistentJsonStore;
import com.hxtech.offer.service.misc.SharedPreferencesJsonStore;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.SpiceRequest;

// Referenced classes of package com.rjfittime.app.service.base:
// CoreService

public class JsonStoreExtension extends CoreService.Extension
{
  public static interface Client
  {

    public abstract void setPersistentJsonStore(PersistentJsonStore persistentjsonstore);
  }


  private ObjectMapper mObjectMapper;
  private PersistentJsonStore mPersistentJsonStore;

  public JsonStoreExtension(CoreService coreservice, ObjectMapper objectmapper)
  {
    super(coreservice);
    mObjectMapper = objectmapper;
  }

  public ObjectMapper getObjectMapper()
  {
    if (mObjectMapper == null)
    {
      mObjectMapper = new ObjectMapper();
    }
    return mObjectMapper;
  }

  public void onAddRequest(SpiceRequest spicerequest)
  {
    if (spicerequest instanceof Client)
    {
      ((Client) spicerequest).setPersistentJsonStore(mPersistentJsonStore);
    }
  }

  public void onCacheManager(Application application, CacheManager cachemanager)
      throws CacheCreationException
  {}

  public void onCreate()
  {
    mPersistentJsonStore =
        new SharedPreferencesJsonStore(getContext(), getObjectMapper(), (new StringBuilder())
            .append(getContext().getPackageName()).append(".storage").toString());
  }

  public void onDestroy()
  {}
}
