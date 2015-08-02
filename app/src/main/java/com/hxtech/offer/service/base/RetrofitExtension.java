package com.hxtech.offer.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.request.SpiceRequest;

import retrofit.RestAdapter;

// Referenced classes of package com.rjfittime.app.service.base:
// CoreService

public abstract class RetrofitExtension extends CoreService.Extension
{
  public static interface Client
  {

    public abstract void addRetrofitExtension(RetrofitExtension retrofitextension);
  }


  private RestAdapter mRestAdapter;
  private retrofit.RestAdapter.Builder mRestAdapterBuilder;
  private List mRetrofitInterfaceList;
  private Map mRetrofitInterfaceToServiceMap;

  public RetrofitExtension(CoreService coreservice)
  {
    super(coreservice);
    mRetrofitInterfaceList = new ArrayList();
    mRetrofitInterfaceToServiceMap = new HashMap();
  }

  protected void addRetrofitInterface(Class class1)
  {
    mRetrofitInterfaceList.add(class1);
  }

  protected RestAdapter createRestAdapter()
  {
    return getRestAdapterBuilder().build();
  }

  protected abstract retrofit.RestAdapter.Builder createRestAdapterBuilder();

  protected final retrofit.RestAdapter.Builder getRestAdapterBuilder()
  {
    if (mRestAdapterBuilder == null)
    {
      mRestAdapterBuilder = createRestAdapterBuilder();
    }
    return mRestAdapterBuilder;
  }

  public final List getRetrofitInterfaceList()
  {
    return mRetrofitInterfaceList;
  }

  protected Object getRetrofitService(Class class1)
  {
    Object obj1 = mRetrofitInterfaceToServiceMap.get(class1);
    Object obj = obj1;
    if (obj1 == null)
    {
      obj = obj1;
      if (mRetrofitInterfaceList.contains(class1))
      {
        obj = mRestAdapter.create(class1);
        mRetrofitInterfaceToServiceMap.put(class1, obj);
      }
    }
    return obj;
  }

  public void onAddRequest(SpiceRequest spicerequest)
  {
    if (spicerequest instanceof Client)
    {
      ((Client) spicerequest).addRetrofitExtension(this);
    }
  }

  public void onCacheManager(Application application, CacheManager cachemanager)
      throws CacheCreationException
  {}

  public void onCreate()
  {
    mRestAdapter = createRestAdapter();
  }

  public void onDestroy()
  {}
}
