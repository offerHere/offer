package com.hxtech.offer.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxtech.offer.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CollectionFragment extends BaseFragment {

    public static CollectionFragment newInstance(){
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }
}
