package com.hxtech.offer.service.request;

import com.hxtech.offer.service.api.ApiListRequest;
import com.hxtech.offer.service.api.OfferInterface;

/**
 * Created by niejunhong on 15-8-2.
 */
public class GetListRepo extends ApiListRequest {

  private final String userName;

  public GetListRepo(String userName) {
    this.userName = userName;
  }

  @Override
  public Object loadDataFromNetwork() throws Exception {
    return ((OfferInterface) getService(OfferInterface.class)).listRepos(userName);
  }

}
