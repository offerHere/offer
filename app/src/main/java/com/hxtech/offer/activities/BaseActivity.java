package com.hxtech.offer.activities;

import android.app.Activity;
import android.os.Bundle;

import com.hxtech.offer.utils.LogUtils;

/**
 * Created by haoozhou on 2015/7/4.
 */
public class BaseActivity extends Activity {

    private String TAG = getClass().getSimpleName();
    protected void debug(String msg){
        LogUtils.debug(TAG, msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
