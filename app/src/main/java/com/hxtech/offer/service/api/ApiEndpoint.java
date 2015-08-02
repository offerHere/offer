package com.hxtech.offer.service.api;

import android.content.Context;

import retrofit.Endpoint;

public class ApiEndpoint
    implements Endpoint
{

  public static final int API_SERVER_ID = 0;
  public static final String HOST[] = {
      "api.github.com", "api.github.com"
  };
  public static final String KEY_SERVER_HOST_ID = "server_host_id";
  public static final String NAME[] = {
      "Product(api.github.com)", "Debug(api.github.com)"
  };
  public static final String SP_RUNTIME_CONFIG = "_runtime_conf";
  public static final int TEST_SERVER_ID = 1;
  private static int sCurrentHostId = -1;
  private final Context mContext;

  public ApiEndpoint(Context context)
  {
    mContext = context.getApplicationContext();
  }

  public static int getServerHostId(Context context)
  {
    if (sCurrentHostId == -1)
    {
      sCurrentHostId =
          Math.max(0, Math.min(
              context.getSharedPreferences("_runtime_conf", 0).getInt("server_host_id", 0),
              HOST.length));
    }
    return sCurrentHostId;
  }

  public static void setServerHostId(Context context, int i)
  {
    context.getSharedPreferences("_runtime_conf", 0).edit()
        .putInt("server_host_id", Math.max(0, Math.min(i, HOST.length))).commit();
    sCurrentHostId = -1;
  }

  public String getName()
  {
    return NAME[getServerHostId(mContext)];
  }

  public String getUrl()
  {
    return (new StringBuilder()).append("https://").append(HOST[getServerHostId(mContext)])
        .toString();
  }

}
