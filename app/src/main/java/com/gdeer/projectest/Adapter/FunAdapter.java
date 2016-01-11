package com.gdeer.projectest.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdeer.projectest.R;
import com.gdeer.projectest.model.ItemFun;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gdeer on 2016/1/2.
 */
public class FunAdapter extends RecyclerView.Adapter<FunAdapter.ViewHolder> {

    private List<ItemFun> mDataList;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public FunAdapter(List<ItemFun> mDataList) {
        this.mDataList = mDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView pIcon;
        public TextView pName;
        public TextView pTime;
        public TextView pContent;
        public ViewHolder(final View itemView) {
            super(itemView);
            this.pIcon = (CircleImageView) itemView.findViewById(R.id.publisher_icon);
            this.pName = (TextView) itemView.findViewById(R.id.publisher_name);
            this.pTime = (TextView) itemView.findViewById(R.id.publish_time);
            this.pContent = (TextView) itemView.findViewById(R.id.publish_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //子View被点击之后，启动自定义接口。
                    mItemClickListener.onItemClick(itemView, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemLongClickListener.onItemLongClick(itemView, getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    public interface MyItemLongClickListener {
        void onItemLongClick(View view, int postion);
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
//        View view = View.inflate(parent.getContext(), R.layout.item_fun, null);
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fun, parent, false);
        holder = new ViewHolder(view1);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.pName.setText(mDataList.get(position).getPublisherName());
        holder.pTime.setText(mDataList.get(position).getPublishTime());
        holder.pContent.setText(mDataList.get(position).getPublishContent());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
