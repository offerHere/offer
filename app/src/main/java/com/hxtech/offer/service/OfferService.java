package com.hxtech.offer.service;

import com.hxtech.offer.service.api.ApiRetrofitExtension;
import com.hxtech.offer.service.base.ApplicationContextExtension;
import com.hxtech.offer.service.base.CacheManagerExtension;
import com.hxtech.offer.service.base.CascadeExtension;
import com.hxtech.offer.service.base.CoreService;
import com.hxtech.offer.service.base.JsonStoreExtension;
import com.hxtech.offer.service.misc.AutoMapper;

/**
 * Created by niejunhong on 15-8-2.
 */
public class OfferService extends CoreService {


  @Override
  public void onInitializeExtension() {
    addExtension(new ApiRetrofitExtension(this));
    addExtension(new ApplicationContextExtension(this));
    addExtension(new CacheManagerExtension(this));
    addExtension(new JsonStoreExtension(this, AutoMapper.getInstance()));
    addExtension(new CascadeExtension(this));
  }
}
