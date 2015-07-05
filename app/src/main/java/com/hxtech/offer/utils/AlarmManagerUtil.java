package com.hxtech.offer.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.hxtech.offer.receivers.AlarmMessageReceiver;

import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by linjinquan01 on 2015/7/4.
 */
public class AlarmManagerUtil {

    /**
     * 该函数用于启动一个闹钟，在指定的时间发送一个广播，让广播发出Notification
     *
     * @param context
     * @param params  ， {"iconResourceId":xxx,"title":"xxxx","content":"xxxx","ActivityClassName","otherField_1":"xxx",....}
     *                这个里面，iconResourceId，title，content，ActivityClassName是必要的，otherField_1这个是用于在启动activity时，传送给下一个
     *                的参数
     *                ActivityClassName 使用这个函数获取：xxx.class.getName();
     * @param dateStr ， 指定的启动这个activity的日志，格式为：yyyy-mm-dd HH:ii:ss
     */
    public void setAlarmNotification(Context context, Map<String, String> params, String dateStr) {
        Intent intent = new Intent(context, AlarmMessageReceiver.class);
        intent.setAction(OfferConstant.ACTION_ALARM_NOTIFICATION);
        ArrayList<String> keyList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            intent.putExtra(entry.getKey(),entry.getValue());
            keyList.add(entry.getKey());
        }
        intent.putStringArrayListExtra("keyList",keyList);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, DateTime.parse(dateStr).getMillis(), pendingIntent);
    }
}
