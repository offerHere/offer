package com.hxtech.offer.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxtech.offer.R;
import com.hxtech.offer.models.NavDrawerItem;
import com.hxtech.offer.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoozhou on 2015/7/4.
 */
public abstract class BaseSlideMenuActivity extends BaseActivity {



    private List<NavDrawerItem> mNavDrawerItems = new ArrayList<>();
    private List<View> mNavDrawerItemViews = new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    private void setupNavDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(getResources().getColor(R.color.navdrawer_scrim_color));
        mDrawerToggle.syncState();
        populateNavDrawer();
    }

    private void populateNavDrawer(){
        mNavDrawerItems.clear();
        int[] navItemIdSet = getNavItemIdSet();
        for (int i = 0; navItemIdSet != null && i < navItemIdSet.length; i++){
            mNavDrawerItems.add(NavDrawerItem.createNavDrawerItem(getApplicationContext(), navItemIdSet[i]));
        }
        createNavDrawerItems();
    }

    public void createNavDrawerItems(){
        ViewGroup mNavDrawerItemContainer = (ViewGroup) findViewById(R.id.nav_drawer_item_container);
        if (mNavDrawerItemContainer == null){
            throw new RuntimeException("can not found nav_drawer_item_container");
        }
        mNavDrawerItemContainer.removeAllViews();
        mNavDrawerItemViews.clear();
        for (NavDrawerItem item: mNavDrawerItems){
            View navDrawerViewItem = makeNavDrawerItemView(item, mNavDrawerItemContainer);
            mNavDrawerItemContainer.addView(navDrawerViewItem);
            mNavDrawerItemViews.add(navDrawerViewItem);
        }
    }

    public void updateNavDrawerItems(){
        populateNavDrawer();
    }

    abstract public View makeNavDrawerItemView(NavDrawerItem navDrawerItem, ViewGroup parent);

    protected void setOnClickListener(final View navDrawerViewItem, final NavDrawerItem navDrawerItem){
        navDrawerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDrawerItemSelected(navDrawerViewItem, navDrawerItem);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    abstract  public void onDrawerItemSelected(View navDrawerViewItem, NavDrawerItem selectedNavDrawerItem);

    abstract public  int[] getNavItemIdSet();

    public void onDrawerOpened(View drawerView){
        debug("onDrawerOpened");
    }

    public void onDrawerClosed(View drawerView){
        debug("onDrawerClosed");
    }

    public void openDrawer(){
        if (mDrawerLayout != null){
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public void closeDrawer(){
        if (mDrawerLayout != null){
            mDrawerLayout.closeDrawers();
        }
    }
}
