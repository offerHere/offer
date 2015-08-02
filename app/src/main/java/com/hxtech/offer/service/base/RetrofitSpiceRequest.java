package com.hxtech.offer.service.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.octo.android.robospice.request.SpiceRequest;

// Referenced classes of package com.rjfittime.app.service.base:
// RetrofitExtension

public abstract class RetrofitSpiceRequest<T> extends SpiceRequest
    implements RetrofitExtension.Client
{

  private List mRetrofitExtensionList;

  public RetrofitSpiceRequest(T t)
  {
    super(t.getClass());
    mRetrofitExtensionList = new ArrayList();
  }

  public void addRetrofitExtension(RetrofitExtension retrofitextension)
  {
    if (!mRetrofitExtensionList.contains(retrofitextension))
    {
      mRetrofitExtensionList.add(retrofitextension);
    }
  }

  public Object getService(Class class1)
  {
    for (Iterator iterator = mRetrofitExtensionList.iterator(); iterator.hasNext();)
    {
      Object obj = ((RetrofitExtension) iterator.next()).getRetrofitService(class1);
      if (obj != null)
      {
        return obj;
      }
    }

    return null;
  }
}
