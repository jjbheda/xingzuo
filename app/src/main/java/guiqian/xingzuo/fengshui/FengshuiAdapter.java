package guiqian.xingzuo.fengshui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import guiqian.xingzuo.R;

/**
 * Created by jiangjingbo on 2017/9/6.
 */

public class FengshuiAdapter extends BaseAdapter {
    private Activity context;
    private  List<DYXZModel> modelList = new ArrayList<>();
    private LayoutInflater mInflater;

    public FengshuiAdapter(Activity activity, List<DYXZModel> modelList){
        this.context = activity;
        this.modelList = modelList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return modelList.size() ;
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
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.fengshui_layoutem_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_fengshui_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_fengshui_content);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final DYXZModel model = modelList.get(position);
        viewHolder.title.setText(model.title);
        viewHolder.content.setText(model.content);
        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FengShuiDetailActivity.newInstance((Activity) context,model.url);
            }
        });
        return convertView;
    }

    private static class ViewHolder{
        TextView title;
        TextView content;

    }
}
