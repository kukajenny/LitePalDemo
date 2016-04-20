package com.example.zjp.litepaldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zjp.litepaldemo.R;
import com.example.zjp.litepaldemo.model.News;

import java.util.List;

/**
 * Created by zjp on 16-4-19.
 */
public class NewsAdapter extends ArrayAdapter<News>{

    private int resourceId;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }
    public View getView(int position,View convertView,ViewGroup parent){
        News news = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView item_title = (TextView)view.findViewById(R.id.item_title);
        item_title.setText(news.getTitle());
        TextView item_content = (TextView) view.findViewById(R.id.item_content);
        item_content.setText(news.getContent());
        return view;
    }
}
