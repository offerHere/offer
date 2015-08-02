package com.hxtech.offer.service.misc;

import android.os.Build;

import retrofit.RequestInterceptor;

public class ClassicalUserAgentRequestInterceptor
    implements RequestInterceptor
{

  public static final String USER_AGENT;

  public ClassicalUserAgentRequestInterceptor()
  {}

  public void intercept(retrofit.RequestInterceptor.RequestFacade requestfacade)
  {
    requestfacade.addHeader("User-Agent", USER_AGENT);
  }

  static
  {
    USER_AGENT =
        String.format(
            "%s/%s (%s; %s; %s; %s)",
            new Object[] {
                "com.hxtech.offer", "1.0.1", Build.MODEL,
                System.getProperties().getProperty("java.vm.name", "Unknown JVM"),
                System.getProperties().getProperty("os.arch", "Unknown Architecture"),
                System.getProperties().getProperty("os.name", "Unknown OS")
            });
  }
}
