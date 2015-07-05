package com.hxtech.offer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;

/**
 * Created by linjinquan01 on 2015/7/4.
 */
public class PushMessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConstants.ACTION_MESSAGE)) {
            String message = intent.getExtras().getString(
                    PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            Log.e("message","message : "+message);
        } else if (intent.getAction().equals(PushConstants.ACTION_RECEIVE)) {
            // 处理绑定等方法的返回数据
            // PushManager.startWork()的返回值通过PushConstants.METHOD_BIND得到

            // 获取方法
            final String method = intent
                    .getStringExtra(PushConstants.EXTRA_METHOD);
            // 方法返回错误码。若绑定返回错误（非0），则应用将不能正常接收消息。
            // 绑定失败的原因有多种，如网络原因，或access token过期。
            // 请不要在出错时进行简单的startWork调用，这有可能导致死循环。
            // 可以通过限制重试次数，或者在其他时机重新调用来解决。
            int errorCode = intent.getIntExtra(PushConstants.EXTRA_ERROR_CODE,
                    PushConstants.ERROR_SUCCESS);
            String content = "";
            if (intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT) != null) {
                // 返回内容
                content = new String(
                        intent.getByteArrayExtra(PushConstants.EXTRA_CONTENT));
                Log.e("Test","content : "+content);
            }
        }else if (intent.getAction().equals(
                PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)){
            String customContent = intent.getExtras().getString(
                    PushConstants.EXTRA_EXTRA, "");
            Log.e("cus","cue : "+customContent);
        }
    }
}
