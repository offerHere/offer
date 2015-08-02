package com.hxtech.offer.app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;

// Referenced classes of package com.rjfittime.app.foundation:
// ApplicationComponentControlAgent, ApplicationComponentControlProxy

public abstract class ApplicationComponentContextBase
    implements ApplicationComponentControlAgent
{
  public static class ObjectWrapper
      implements ApplicationComponentControlProxy
  {

    private final Object mObject;

    public void create(Context context)
    {}

    public void destroy()
    {}

    public Object instance()
    {
      return mObject;
    }

    public void start(Context context)
    {}

    public void stop()
    {}

    public ObjectWrapper(Object obj)
    {
      mObject = obj;
    }
  }


  private static final int STATE_CREATED = 1;
  private static final int STATE_DESTROY = 4;
  private static final int STATE_STARTED = 2;
  private static final int STATE_STOPPED = 3;
  private static final int STATE_UNKNOWN = 0;
  private final Map mAgents = new HashMap();
  private Context mContext;
  private int mState;

  public ApplicationComponentContextBase()
  {
    mState = STATE_UNKNOWN;
  }

  public void addApplicationService(String s,
      ApplicationComponentControlAgent applicationcomponentcontrolagent) {
    mAgents.put(s, applicationcomponentcontrolagent);
  }

  public void addApplicationService(String s, Object obj)
  {
    addApplicationService(s, ((ApplicationComponentControlAgent) (new ObjectWrapper(obj))));
  }

  public void create(Context context)
  {
    mContext = context;
    Iterator iterator = mAgents.values().iterator();
    for (; iterator.hasNext(); ((ApplicationComponentControlAgent) iterator.next())
        .create(mContext)) {}
    mState = STATE_CREATED;
  }

  public void destroy()
  {
    Iterator iterator = mAgents.values().iterator();
    for (; iterator.hasNext(); ((ApplicationComponentControlAgent) iterator
        .next()).destroy()) {}
    mContext = null;
    mState = STATE_DESTROY;
  }

  public Object getApplicationService(String s)
  {
    Object obj = mAgents.get(s);
    return ((ApplicationComponentControlProxy) obj).instance();
  }

  public void start(Context context)
  {
    Iterator iterator = mAgents.values().iterator();
    for (; iterator.hasNext(); ((ApplicationComponentControlAgent) iterator
        .next()).start(mContext)) {}
    mState = STATE_STARTED;
  }

  public void stop()
  {
    Iterator iterator = mAgents.values().iterator();
    for (; iterator.hasNext(); ((ApplicationComponentControlAgent) iterator
        .next()).stop()) {}
    mState = STATE_STOPPED;
  }
}
