package com.hxtech.offer.app;

import android.content.Context;

import com.octo.android.robospice.SpiceManager;

public class ApplicationComponentContext extends ApplicationComponentContextBase
{
  public static class SpiceServiceProxy
      implements ApplicationComponentControlProxy
  {

    private final SpiceManager mSpiceManager;

    public void create(Context context)
    {}

    public void destroy()
    {}

    public Object instance()
    {
      return mSpiceManager;
    }

    public void start(Context context)
    {
      mSpiceManager.start(context);
    }

    public void stop()
    {
      mSpiceManager.shouldStop();
    }

    public SpiceServiceProxy(Class class1)
    {
      mSpiceManager = new SpiceManager(class1);
    }
  }


  public ApplicationComponentContext()
  {}
}
