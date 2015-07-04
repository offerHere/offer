package com.hxtech.offer.fragments;


import android.support.v4.app.Fragment;

import com.hxtech.offer.utils.LogUtils;

/**
 * Created by haoozhou on 2015/7/4.
 */
public class BaseFragment extends Fragment {
    private String TAG = getClass().getSimpleName();
    protected void debug(String msg){
        LogUtils.debug(TAG, msg);
    }

}
