package com.hxtech.offer.models;

import android.content.Context;

import com.hxtech.offer.R;

/**
 * Created by zhouhao on 2015/4/29.
 */
public class NavDrawerItem {

    public static final int ITEM_INVALID = -1;
    //首页
    public static final int ITEM_PORTAL = 10;
    //关注的公司
    public static final int ITEM_COMPANY = 20;
    //收藏
    public static final int ITEM_COLLECTION = 30;
    //面经，笔试题入口
    public static final int ITEM_CANON = 40;
    //意见反馈
    public static final int ITEM_FEEDBACK = 50;
    //关于我们，是版本更新，APP分享的入口
    public static final int ITEM_ABOUT_US = 60;
    //设置
    public static final int ITEM_SETTINGS = 70;

    private int mItemId;
    private int mIconResId;
    private String mLabel;

    public static NavDrawerItem createNavDrawerItem(Context ctx, int itemId){
        if (!isValidItemId(itemId)){
            throw new RuntimeException("invalid nav drawer item id");
        }
        NavDrawerItem item = null;
        switch (itemId){
            case ITEM_PORTAL:
                item = new NavDrawerItem(ITEM_PORTAL, 0, ctx.getString(R.string.drawable_menu_item_portal));
                break;
            case ITEM_COMPANY:
                item = new NavDrawerItem(ITEM_COMPANY, 0, ctx.getString(R.string.drawable_menu_item_company));
                break;
            case ITEM_COLLECTION:
                item = new NavDrawerItem(ITEM_COLLECTION, 0, ctx.getString(R.string.drawable_menu_item_collection));
                break;
            case ITEM_CANON:
                item = new NavDrawerItem(ITEM_CANON, 0, ctx.getString(R.string.drawable_menu_item_canon));
                break;
            case ITEM_FEEDBACK:
                item = new NavDrawerItem(ITEM_FEEDBACK, 0, ctx.getString(R.string.drawable_menu_item_feedback));
                break;
            case ITEM_ABOUT_US:
                item = new NavDrawerItem(ITEM_ABOUT_US, 0, ctx.getString(R.string.drawable_menu_item_about_us));
                break;
            case ITEM_SETTINGS:
                item = new NavDrawerItem(ITEM_SETTINGS, 0, ctx.getString(R.string.drawable_menu_item_settings));
                break;
        }
        return item;
    }

    private NavDrawerItem(int itemId, int iconResId, String label){

        mItemId = itemId;
        mIconResId = iconResId == 0 ? R.drawable.ic_launcher : iconResId;
        mLabel = label;
    }

    private static boolean isValidItemId(int itemId){
        return true;
    }

    public int getIconResId() {
        return mIconResId;
    }

    public void setIconResId(int mIconResId) {
        this.mIconResId = mIconResId;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public int getItemId(){
        return mItemId;
    }
}
