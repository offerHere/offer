package com.hxtech.offer.fragments;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.media.MediaRecorder;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hxtech.offer.R;

import org.w3c.dom.Text;

public class TimelineAdapter extends BaseAdapter {

    public static final String CONTENT_TITLE = "title";
    public static final String CONTENT_TIME = "time";
    public static final String CONTENT_ADDRESS = "address";
    public static final String CONTENT_ACTION_MODE = "actionMode";
    public static final String CONTENT_SITE_ADDRESS = "siteAddress";
    public static final String CONTENT_SHOW_TIME = "showTime";
    private Context context;
    private List<Map<String, String>> list;
    private LayoutInflater inflater;

    public TimelineAdapter(Context context, List<Map<String, String>> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_personal_schedule_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.actionMode = (TextView) convertView.findViewById(R.id.actionMode);
            viewHolder.siteAddress = (TextView) convertView.findViewById(R.id.siteAddress);
            viewHolder.showTime = (TextView) convertView.findViewById(R.id.show_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Map<String, String> contentMap = list.get(position);
        if (contentMap.containsKey(CONTENT_TITLE)) {
            viewHolder.title.setText(contentMap.get(CONTENT_TITLE));
            viewHolder.title.setVisibility(View.VISIBLE);
        }
        if (contentMap.containsKey(CONTENT_TIME)) {
            viewHolder.time.setText(contentMap.get(CONTENT_TIME));
            viewHolder.time.setVisibility(View.VISIBLE);
        }
        if (contentMap.containsKey(CONTENT_ADDRESS)) {
            viewHolder.address.setText(contentMap.get(CONTENT_ADDRESS));
            viewHolder.address.setVisibility(View.VISIBLE);
        }
        if (contentMap.containsKey(CONTENT_ACTION_MODE)) {
            viewHolder.actionMode.setText(contentMap.get(CONTENT_ACTION_MODE));
            viewHolder.actionMode.setVisibility(View.VISIBLE);
        }
        if (contentMap.containsKey(CONTENT_SITE_ADDRESS)) {
            viewHolder.siteAddress.setText(contentMap.get(CONTENT_SITE_ADDRESS));
            viewHolder.siteAddress.setVisibility(View.VISIBLE);
        }
        viewHolder.showTime.setText(contentMap.get(CONTENT_SHOW_TIME));
        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView time;
        public TextView address;
        public TextView actionMode;
        public TextView siteAddress;
        public TextView showTime;
    }
}
