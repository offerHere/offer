package com.hxtech.offer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hxtech.offer.R;
import com.hxtech.offer.utils.OfferConstant;
import com.hxtech.offer.utils.PushMessageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linjinquan01 on 2015/7/4.
 */
public class AlarmMessageReceiver extends BroadcastReceiver {

    public static final String EXTRA_KEY_LIST = "keyList";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (OfferConstant.ACTION_ALARM_NOTIFICATION.equalsIgnoreCase(intent.getAction())) {
            ArrayList<String> keyList = intent.getStringArrayListExtra(EXTRA_KEY_LIST);
            Integer iconResourceId = intent.getIntExtra(OfferConstant.EXTRA_ICON_RESOURCE_ID, R.drawable.ic_launcher);
            String title = intent.getStringExtra(OfferConstant.EXTRA_TITLE);
            String content = intent.getStringExtra(OfferConstant.EXTRA_CONTENT);
            String activityClassName = intent.getStringExtra(OfferConstant.EXTRA_ACTIVITY_CLASS_NAME);
            keyList.remove(OfferConstant.EXTRA_TITLE);
            keyList.remove(OfferConstant.EXTRA_CONTENT);
            keyList.remove(OfferConstant.EXTRA_ACTIVITY_CLASS_NAME);
            keyList.remove(OfferConstant.EXTRA_ICON_RESOURCE_ID);
            Intent targetIntent = new Intent();
            try {
                targetIntent.setClass(context, Class.forName(activityClassName));
                targetIntent.putStringArrayListExtra("keyList", keyList);
                for (String key : keyList) {
                    targetIntent.putExtra(key, intent.getStringExtra(key));
                }
                PushMessageUtil.pushNotification(iconResourceId, title, content, context, targetIntent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
