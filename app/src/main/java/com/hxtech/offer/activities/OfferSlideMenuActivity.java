package com.hxtech.offer.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.hxtech.offer.R;
import com.hxtech.offer.fragments.CanonFragment;
import com.hxtech.offer.fragments.CollectionFragment;
import com.hxtech.offer.fragments.CompanyFragment;
import com.hxtech.offer.fragments.PortalFragment;
import com.hxtech.offer.models.NavDrawerItem;
import com.hxtech.offer.utils.AlarmManagerUtil;
import com.hxtech.offer.utils.OfferConstant;
import com.hxtech.offer.utils.PushMessageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niejunhong on 15-7-4.
 */
public class OfferSlideMenuActivity extends BaseSlideMenuActivity {

    //当前的菜单选中项
    private int mCurtNavItemId = NavDrawerItem.ITEM_INVALID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_slide_menu);
        changeFragment(getInitNavItemId());
        Log.e("CLAANAME",OfferSlideMenuActivity.class.getName());
        Map<String,String> params = new HashMap<String,String>();
        params.put(OfferConstant.EXTRA_ICON_RESOURCE_ID,R.drawable.ic_launcher+"");
        params.put(OfferConstant.EXTRA_ACTIVITY_CLASS_NAME,OfferSlideMenuActivity.class.getName());
        params.put(OfferConstant.EXTRA_TITLE,"test for offer");
        params.put(OfferConstant.EXTRA_CONTENT,"test for time 2015-07-05 13:42:00");
        AlarmManagerUtil.setAlarmNotification(getApplicationContext(),params,"2015-07-05 13:42:00");
    }

    /**
     * 获取当前选中的菜单选项
     *
     * @return
     */
    public int getCurrentNavItemId() {
        return mCurtNavItemId;
    }

    public void setCurrentNavItemId(int itemId) {
        mCurtNavItemId = itemId;
    }

    /**
     * 获取初始化时选中的菜单
     *
     * @return
     */
    private int getInitNavItemId() {
        return NavDrawerItem.ITEM_PORTAL;
    }

    @Override
    public View makeNavDrawerItemView(NavDrawerItem navDrawerItem, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutToInflate = R.layout.nav_drawer_item;
        View view = layoutInflater.inflate(layoutToInflate, parent, false);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(navDrawerItem.getIconResId());
        TextView label = (TextView) view.findViewById(R.id.label);
        label.setText(navDrawerItem.getLabel());
        setOnClickListener(view, navDrawerItem);
        if (navDrawerItem.getItemId() == getCurrentNavItemId()) {
            view.setBackgroundColor(Color.YELLOW);
        }
        return view;
    }

    @Override
    public void onDrawerItemSelected(View navDrawerViewItem, NavDrawerItem selectedNavDrawerItem) {
        changeFragment(selectedNavDrawerItem.getItemId());
        updateNavDrawerItems();
        closeDrawer();
    }

    private void changeFragment(int navItemId) {
        int currentNavId = getCurrentNavItemId();
        switch (navItemId) {
            case NavDrawerItem.ITEM_PORTAL:
                if (currentNavId != NavDrawerItem.ITEM_PORTAL) {
                    showPortalFragment();
                    setCurrentNavItemId(navItemId);
                }
                break;
            case NavDrawerItem.ITEM_COMPANY:
                if (currentNavId != NavDrawerItem.ITEM_COMPANY) {
                    showCompanyFragment();
                    setCurrentNavItemId(navItemId);
                }
                break;
            case NavDrawerItem.ITEM_COLLECTION:
                if (currentNavId != NavDrawerItem.ITEM_COLLECTION) {
                    showCollectionFragment();
                    setCurrentNavItemId(navItemId);
                }
                break;
            case NavDrawerItem.ITEM_CANON:
                if (currentNavId != NavDrawerItem.ITEM_CANON) {
                    showCanonFragment();
                    setCurrentNavItemId(navItemId);
                }
                break;
            case NavDrawerItem.ITEM_FEEDBACK:
                startActivity(FeedbackActivity.class);
                break;
            case NavDrawerItem.ITEM_SETTINGS:
                startActivity(SettingsActivity.class);
                break;
            case NavDrawerItem.ITEM_ABOUT_US:
                startActivity(AboutUsActivity.class);
        }
    }

    /**
     * 返回侧滑菜单列表
     *
     * @return
     */
    @Override
    public int[] getNavItemIdSet() {
        return new int[]{
                NavDrawerItem.ITEM_PORTAL,
                NavDrawerItem.ITEM_COMPANY,
                NavDrawerItem.ITEM_COLLECTION,
                NavDrawerItem.ITEM_CANON,
                NavDrawerItem.ITEM_FEEDBACK,
                NavDrawerItem.ITEM_SETTINGS,
                NavDrawerItem.ITEM_ABOUT_US,
        };
    }

    /**
     * 显示首页
     */
    public void showPortalFragment() {
        PortalFragment fragment = PortalFragment.newInstance();
        showFragment(fragment, true, false);
    }

    /**
     * 显示关注的公司页
     */
    public void showCompanyFragment() {
        CompanyFragment fragment = CompanyFragment.newInstance();
        showFragment(fragment, true, false);
    }

    /**
     * 显示葵花宝典页，面试题，面经入口
     */
    public void showCanonFragment() {
        CanonFragment fragment = CanonFragment.newInstance();
        showFragment(fragment, true, false);
    }

    /**
     * 显示收藏页
     */
    public void showCollectionFragment() {
        CollectionFragment fragment = CollectionFragment.newInstance();
        showFragment(fragment, true, false);
    }

    public void showFragment(Fragment fragment, boolean replace, boolean pushToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (replace) {
            ft.replace(R.id.fragment, fragment, fragment.getClass().getSimpleName());
        } else {
            ft.add(R.id.fragment, fragment, fragment.getClass().getSimpleName());
        }
        if (pushToBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public void startActivity(Class clz) {
        startActivity(new Intent(this, clz));
    }
}
