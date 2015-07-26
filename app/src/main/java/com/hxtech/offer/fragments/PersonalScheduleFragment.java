package com.hxtech.offer.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hxtech.offer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linjinquan01 on 2015/7/26.
 */
public class PersonalScheduleFragment extends ListFragment {

    private List<Map<String,String>> data;
    private TimelineAdapter timelineAdapter;

    public static PersonalScheduleFragment newInstance() {
        PersonalScheduleFragment fragment = new PersonalScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public PersonalScheduleFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
//        listView = (ListView) findViewById(R.id.personal_schedule_listview);
//        listView.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(getActivity(), getData());
//        listView.setAdapter(timelineAdapter);
        setListAdapter(timelineAdapter);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_schedule, container, false);
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put(TimelineAdapter.CONTENT_SHOW_TIME,"8月10号");
        map.put(TimelineAdapter.CONTENT_TITLE, "百度笔试");
        map.put(TimelineAdapter.CONTENT_TIME,"时间：19:00~20:30");
        map.put(TimelineAdapter.CONTENT_ADDRESS,"地点：北京邮电大学教三206");
        map.put(TimelineAdapter.CONTENT_ACTION_MODE,"形式：现场笔试");
        map.put(TimelineAdapter.CONTENT_SITE_ADDRESS,"网址：www.baidu.com");
        list.add(map);

        map = new HashMap<String, String>();
        map.put(TimelineAdapter.CONTENT_SHOW_TIME,"8月13号");
        map.put(TimelineAdapter.CONTENT_TITLE, "阿里巴巴宣讲会");
        map.put(TimelineAdapter.CONTENT_TIME,"时间：16:00~18:00");
        list.add(map);

        map = new HashMap<String, String>();
        map.put(TimelineAdapter.CONTENT_SHOW_TIME,"8月15号");
        map.put(TimelineAdapter.CONTENT_TITLE, "这是第3行测试数据");
        list.add(map);

        map = new HashMap<String, String>();
        map.put(TimelineAdapter.CONTENT_SHOW_TIME,"8月19号");
        map.put(TimelineAdapter.CONTENT_TITLE, "这是第4行测试数据");
        list.add(map);
        return list;
    }
}
