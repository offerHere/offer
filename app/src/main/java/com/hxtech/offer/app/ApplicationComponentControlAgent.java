// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.hxtech.offer.app;

import android.content.Context;

public interface ApplicationComponentControlAgent
{

    public abstract void create(Context context);

    public abstract void destroy();

    public abstract void start(Context context);

    public abstract void stop();
}
