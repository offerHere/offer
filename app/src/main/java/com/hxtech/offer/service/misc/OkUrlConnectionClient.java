
package com.hxtech.offer.service.misc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

public class OkUrlConnectionClient extends UrlConnectionClient
{

  private final OkUrlFactory mURLFactory;

  public OkUrlConnectionClient(OkHttpClient okhttpclient)
  {
    mURLFactory = new OkUrlFactory(okhttpclient);
  }

  protected HttpURLConnection openConnection(Request request)
      throws IOException
  {
    return mURLFactory.open(new URL(request.getUrl()));
  }
}
