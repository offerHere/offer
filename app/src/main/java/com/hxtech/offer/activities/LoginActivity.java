package com.hxtech.offer.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.hxtech.offer.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by linjinquan01 on 2015/7/5.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    public static final int QQ_NICK_NAME_MESSAGE = 0;
    @ViewById
    ImageButton qqLoginButton;

    public static QQAuth mQQAuth;

    private static Tencent mTencent;

    private UserInfo mQQUserInfo;

    private static final String APP_ID = "1104754270";

    private static final String APP_KEY = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == QQ_NICK_NAME_MESSAGE) {
                JSONObject response = (JSONObject) msg.obj;
                Log.e("reponse", "resepinse : " + response.toString());
                if (response.has("nickname")) {
                    try {
                        Log.e("QQNIckName", "nickname : " + response.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        mQQAuth = QQAuth.createInstance(APP_ID, getApplicationContext());
        mTencent = Tencent.createInstance(APP_ID, getApplicationContext());
        super.onStart();
    }

    public void onClickQQLogin(View v) {
        Log.e("TARG", "QQ login click");
        IUiListener listener = new BaseUiListener() {
            @Override
            protected void doComplete(JSONObject values) {
                Log.e("docomplete ", values.toString());
                updateUseInfo();
            }
        };
        mQQAuth.login(this, "all", listener);
        mTencent.login(this, "all", listener);
    }

    private void updateUseInfo() {
        Log.e("TAG", "login success,update useInfo : " + mQQAuth.isSessionValid());
        if (mQQAuth != null) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onComplete(Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = QQ_NICK_NAME_MESSAGE;
                    handler.sendMessage(msg);
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            };
            mQQUserInfo = new UserInfo(this, mQQAuth.getQQToken());
            mQQUserInfo.getUserInfo(listener);
        }
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Util.showAlert(LoginActivity.this, response.toString(),
                    "登录成功");
            doComplete((JSONObject) response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
            Util.showAlert(LoginActivity.this, "onError: " + e.errorDetail, "test");
        }

        @Override
        public void onCancel() {
            Util.showAlert(LoginActivity.this, "onCancel: ", "test");
        }
    }
}
