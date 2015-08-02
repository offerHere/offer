package com.hxtech.offer.service.api;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.rjfittime.app.service.api:
// ApiRequest

public abstract class ApiListRequest extends ApiRequest
{

  public ApiListRequest()
  {
    super(List.class);
  }

  public Object getCacheKey(Object obj)
  {
    return null;
  }

  public List putAllToCache(List list)
      throws Exception
  {
    Iterator iterator = list.iterator();
    do
    {
      if (!iterator.hasNext())
      {
        break;
      }
      Object obj = iterator.next();
      Object obj1 = getCacheKey(obj);
      if (obj1 != null)
      {
        getCacheManager().saveDataToCacheAndReturnData(obj, obj1);
      }
    } while (true);
    return list;
  }
}
