package com.hxtech.offer.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hxtech.offer.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by linjinquan01 on 2015/7/5.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    public static final int QQ_NICK_NAME_MESSAGE = 0;


    @ViewById
    Button loginQQ;

    @ViewById
    Button loginWeibo;

    private static final String QQ_APP_ID = "1104754270";

    private static final String WEIBO_APP_KEY = "3699097253";

    private static final String WIEBO_REDIRECT_URL = "http://www.sina.com";

    private static final String WEIBO_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void onClickQQLogin(View view) {
        login(QQ.NAME);
    }

    public void onClickWeiboLogin(View view) {
        login(SinaWeibo.NAME);
    }

    public void logoutBtn(View view) {
        logout(SinaWeibo.NAME);
    }

    private void login(String platformName) {
        Platform platform = ShareSDK.getPlatform(platformName);
        //sina.removeAccount();
        if (platform.isValid()) {
            String userId = platform.getDb().getUserId();
            String name = platform.getDb().getUserName();
            Toast.makeText(this, "name:" + name + ", id = " + userId, Toast.LENGTH_SHORT).show();
            return;
        }
        platform.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform platform, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onComplete(final Platform platform, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
                        String userId = platform.getDb().getUserId();
                        String name = platform.getDb().getUserName();
                        Toast.makeText(LoginActivity.this, "name:" + name + ", id = " + userId, Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onCancel(Platform platform, int arg1) {
                // TODO Auto-generated method stub
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        //sina.authorize();
        platform.SSOSetting(false);
        platform.showUser(null);
    }


    private void logout(String platform) {
        ShareSDK.getPlatform(this, platform).removeAccount();
    }

}
