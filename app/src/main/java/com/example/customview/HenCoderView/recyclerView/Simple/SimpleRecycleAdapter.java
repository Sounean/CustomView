package com.example.customview.HenCoderView.recyclerView.Simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customview.R;

import java.util.ArrayList;

/**
 * @Author : Sounean
 * @Time : On 2022-05-06 10:28
 * @Description : SimpleRecycleAdapter
 * @Warn :
 */
public class SimpleRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mDatas;

    public static enum ITEM_TYPE {  //2.两种不同类型的item
        ITEM_TYPE_SECTION,
        ITEM_TYPE_ITEM
    }
    private int M_SECTION_ITEM_NUM = 10;

    @Override
    public int getItemViewType(int position) {
        if (position % M_SECTION_ITEM_NUM == 0){
            return ITEM_TYPE.ITEM_TYPE_SECTION.ordinal();
        }
        return ITEM_TYPE.ITEM_TYPE_ITEM.ordinal();
    }

    public SimpleRecycleAdapter(Context context, ArrayList<String> datas) { // 1.自定义一个构造函数，接受外部的数据
        mContext = context;
        mDatas = datas;
    }


    //1.ViewHolder的主要作用就是将XML中的控件以变量的形式保存起来,方便我们后面数据绑定
    //在创建ViewHolder时,将整个ItemView传了进来,然后将TextView从ItemView中取出来保存在mTV变量中.并且,在点击mTV后,弹出这个TextView的内容.
    public class SimpleHolder extends RecyclerView.ViewHolder{
        public TextView mTV;

        public SimpleHolder(View itemView) {
            super(itemView);

            mTV = (TextView) itemView.findViewById(R.id.item_tv);
            mTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,mTV.getText(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    //2.如果有第二种item格式的话还需要另外一个hodler
    public class SimpleHolder02 extends RecyclerView.ViewHolder{
        public TextView mSecondTv;
        public SimpleHolder02(@NonNull View itemView) {
            super(itemView);
            mSecondTv = itemView.findViewById(R.id.item_second_tv);
        }
    }


    /*
    * 0.用于得到我们自定义的ViewHolder,在listView中,我们也会定义ViewHolder来承载视图中的元素.
    * viewType用来根据类型的不同，传进去不同的参数来实现不同的item有不同的布局
    * onCreateViewHolder会在创建一个新View的时候调用，如果是复用的View，就只会调用onBindViewHolder而不会调用onCreateViewHolder
    * */
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        return new SimpleHolder(inflater.inflate(R.layout.item_simple_recycle,parent,false));
//    }
    //2.比上方多了判断item是属于哪个类型
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == ITEM_TYPE.ITEM_TYPE_ITEM.ordinal()){
            return new SimpleHolder(inflater.inflate(R.layout.item_simple_recycle,parent,false));
        }
        return new SimpleHolder02(inflater.inflate(R.layout.item_simple_recycle02,parent,false));
    }


    /*
    * 0.是用于将指定位置的数据和视图绑定起来的
    * 如果是新创建的View，则会先调用onCreateViewHolder来创建View，然后调用onBindViewHolder来绑定数据，
    * 如果是复用的View，就只会调用onBindViewHolder而不会调用onCreateViewHolder
    * */
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        SimpleHolder simpleHolder = (SimpleHolder) holder;
//        simpleHolder.mTV.setText(mDatas.get(position));
//    }
    //2.通过判断holder类型来实现不同的绑定
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  SimpleHolder02){
            SimpleHolder02 sectionHolder = (SimpleHolder02)holder;
            sectionHolder.mSecondTv.setText("第 "+position/M_SECTION_ITEM_NUM +" 组");
        }else if (holder instanceof  SimpleHolder){
            SimpleHolder normalHolder = (SimpleHolder)holder;
            normalHolder.mTV.setText(mDatas.get(position));
        }
    }


    /*
    * 0.getItemCount:用于获取列表总共的item数
    * */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
