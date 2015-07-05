package com.hxtech.offer.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by linjinquan01 on 2015/7/5.
 */
@SharedPref
public interface PerFerUtil {

    @DefaultString("")
    String qqOpenId();  // 用于唯一标识用户身份（每一个openid与QQ号码对应）

    @DefaultString("")
    String qqNickName();    // QQ的昵称
}
