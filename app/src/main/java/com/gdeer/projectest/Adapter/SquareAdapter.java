package com.gdeer.projectest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdeer.projectest.R;
import com.gdeer.projectest.model.ItemSquare;

import java.util.List;


public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder> {

    private List<ItemSquare> sDatalist;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public SquareAdapter(List<ItemSquare> squareDatalist) {
        sDatalist = squareDatalist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(final View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.cv_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(itemView,getAdapterPosition());
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
        void onItemClick(View view,int postion);
    }

    public interface MyItemLongClickListener {
        void onItemLongClick(View view,int postion);
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }
    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }

    @Override
    public SquareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_single_text, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.mTextView.setText(sDatalist.get(position).getItemName());
    }

    @Override
    public int getItemCount() {
        return sDatalist.size();
    }
}