package com.hxtech.offer.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hxtech.offer.R;

/**
 * Created by linjinquan01 on 2015/7/4.
 */
public class PushMessageUtil {

    /**
     * 该方法用于启动百度云推送服务
     * @param context
     */
    public static void startPushService(Context context) {
        PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, "api_key");
    }

    /**
     * 该方法用于主动向通知栏发送Notification
     * @param iconResourceId ，图片的resourcesId
     * @param title , Notification中的标题
     * @param content ，Notification中的内容
     * @param context
     * @param intent ，点击Notification时，具体启动哪个activity
     */
    public static void pushNotification(int iconResourceId, String title, String content, Context context, Intent intent) {
        try {
            Notification mNotification = new Notification();
            mNotification.icon = iconResourceId;
            mNotification.tickerText = title;
            mNotification.when = System.currentTimeMillis();
//        mNotification.sound =  Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.email);
            mNotification.defaults = Notification.DEFAULT_VIBRATE;
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;
            RemoteViews contentView = new RemoteViews(context.getPackageName(),
                    R.layout.notification_view);
            contentView.setTextViewText(R.id.notificationTitle, title);
            contentView.setTextViewText(R.id.notificationContent,
                    content);
            mNotification.contentView = contentView;
            PendingIntent pt = PendingIntent.getActivity(context,
                    content.hashCode(),
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mNotification.contentIntent = pt;
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(
                    content.hashCode(), mNotification);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
