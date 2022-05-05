package com.example.customview.HenCoderView.ScrollListView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.customview.HenCoderView.ScrollListView.view.itemScrollLinearLayout;
import com.example.customview.R;

import java.util.ArrayList;
import java.util.List;


public class DelScrollListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataHolder> mDataList = new ArrayList<DataHolder>();
    private View.OnClickListener mDelClickListener;
    private itemScrollLinearLayout.OnScrollListener mScrollListener;    // 每个item的监听器

    public DelScrollListViewAdapter(Context context, List<DataHolder> dataList,View.OnClickListener delClickListener
            ,itemScrollLinearLayout.OnScrollListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        if (dataList != null && dataList.size() > 0) {
            mDataList.addAll(dataList);
        }
        mDelClickListener = delClickListener;
        mScrollListener=listener;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }


    @Override
    public Object getItem(int position) {   // 调用getItemAtPosition(position)时，返回的是这里的内容
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = mInflater.inflate(R.layout.item_dellistview_layout, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataHolder item = mDataList.get(position);
        holder.title.setText(item.title);   // 对应的item设置text

        item.rootView = (itemScrollLinearLayout)convertView.findViewById(R.id.lin_root);
        //item.rootView =  (itemScrollLinearLayout)convertView.findViewById(R.id.lin_root);
        item.rootView.scrollTo(0, 0);
        item.rootView.setOnScrollListener(mScrollListener);

        TextView delTv = convertView.findViewById(R.id.del);
        delTv.setOnClickListener(mDelClickListener);
        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
    }

    public static class DataHolder {
        public String title;
        public itemScrollLinearLayout rootView;
    }

    public void removeItem(int position){   // 对外提供的删除指定item数据的接口(也可以直接在getVIew里去除，但是给出对外的接口处理更好)
        mDataList.remove(position);
        notifyDataSetChanged();
    }

}
