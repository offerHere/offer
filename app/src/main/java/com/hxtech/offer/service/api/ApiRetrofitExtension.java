package com.hxtech.offer.service.api;

import java.util.concurrent.TimeUnit;

import com.hxtech.offer.service.base.CoreService;
import com.hxtech.offer.service.base.RetrofitExtension;
import com.hxtech.offer.service.misc.AutoMapper;
import com.hxtech.offer.service.misc.ClassicalUserAgentRequestInterceptor;
import com.hxtech.offer.service.misc.CustomJacksonConverter;
import com.hxtech.offer.service.misc.OkUrlConnectionClient;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Endpoint;
import retrofit.converter.Converter;


// Referenced classes of package com.rjfittime.app.service.api:
// ApiEndpoint, FitTimeInterface, WorkoutInterface

public class ApiRetrofitExtension extends RetrofitExtension
{

  private static final int CONNECT_TIMEOUT_MILLIS = 2000;
  private static final int READ_TIMEOUT_MILLIS = 5000;
  private static final int WRITE_TIMEOUT_MILLIS = 5000;
  private Converter mConverter;
  private Endpoint mEndpoint;

  public ApiRetrofitExtension(CoreService coreservice)
  {
    super(coreservice);
  }

  private static OkHttpClient createOkHttpClient()
  {
    OkHttpClient okhttpclient = new OkHttpClient();
    okhttpclient.setConnectTimeout(2000L, TimeUnit.MILLISECONDS);
    okhttpclient.setReadTimeout(5000L, TimeUnit.MILLISECONDS);
    okhttpclient.setWriteTimeout(5000L, TimeUnit.MILLISECONDS);
    return okhttpclient;
  }

  protected retrofit.RestAdapter.Builder createRestAdapterBuilder()
  {
    return (new retrofit.RestAdapter.Builder()).setEndpoint(getEndpoint())
        .setConverter(getConverter())
        .setClient(new OkUrlConnectionClient(createOkHttpClient()))
        .setRequestInterceptor(new ClassicalUserAgentRequestInterceptor());
  }

  public Converter getConverter()
  {
    if (mConverter == null)
    {
      mConverter = new CustomJacksonConverter(AutoMapper.getInstance());
    }
    return mConverter;
  }

  public Endpoint getEndpoint()
  {
    if (mEndpoint == null)
    {
      mEndpoint = new ApiEndpoint(getContext());
    }
    return mEndpoint;
  }

  public void onCreate() {
    super.onCreate();
    addRetrofitInterface(OfferInterface.class);
  }
}
