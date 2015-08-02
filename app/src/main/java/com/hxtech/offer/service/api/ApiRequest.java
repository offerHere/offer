// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc

package com.hxtech.offer.service.api;

import android.content.Context;

import com.hxtech.offer.service.base.ApplicationContextExtension;
import com.hxtech.offer.service.base.CacheManagerExtension;
import com.hxtech.offer.service.base.CascadeExtension;
import com.hxtech.offer.service.base.JsonStoreExtension;
import com.hxtech.offer.service.base.RetrofitSpiceRequest;
import com.hxtech.offer.service.misc.PersistentJsonStore;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.request.SpiceRequest;

public abstract class ApiRequest<T> extends RetrofitSpiceRequest
    implements
    ApplicationContextExtension.Client,
    CacheManagerExtension.Client,
    CascadeExtension.Client,
    JsonStoreExtension.Client
{

  private CacheManager mCacheManager;
  private CascadeExtension mCascadeExtension;
  private Context mContext;
  private PersistentJsonStore mPersistentJsonStore;

  public ApiRequest(T t)
  {
    super(t.getClass());
    setRetryPolicy(null);
  }

  protected Object execute(SpiceRequest spicerequest, Object obj, long l)
      throws Exception
  {
    return mCascadeExtension.execute(spicerequest, obj, l);
  }

  protected Object execute(ApiRequest apirequest, long l)
      throws Exception
  {
    return execute(((SpiceRequest) (apirequest)), apirequest.getCacheKey(), l);
  }

  public Object getCacheKey()
  {
    return null;
  }

  protected CacheManager getCacheManager()
  {
    return mCacheManager;
  }

  protected Context getContext()
  {
    return mContext;
  }

  protected PersistentJsonStore getPersistentJsonStore()
  {
    return mPersistentJsonStore;
  }

  public void setCacheManager(CacheManager cachemanager)
  {
    mCacheManager = cachemanager;
  }

  public void setCascadeExtension(CascadeExtension cascadeextension)
  {
    mCascadeExtension = cascadeextension;
  }

  public void setContext(Context context)
  {
    mContext = context;
  }

  public void setPersistentJsonStore(PersistentJsonStore persistentjsonstore)
  {
    mPersistentJsonStore = persistentjsonstore;
  }
}
