package com.gdeer.projectest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdeer.projectest.R;
import com.gdeer.projectest.model.ItemChat;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gdeer on 2016/1/2.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Viewholder> {

    private List<ItemChat> mDataList;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public ChatAdapter(List<ItemChat> mDataList) {
        this.mDataList = mDataList;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public CircleImageView cIcon;
        public TextView cName;
        public TextView cTime;
        public TextView cLastMessage;
        public Viewholder(final View itemView) {
            super(itemView);
            this.cIcon = (CircleImageView) itemView.findViewById(R.id.chat_icon);
            this.cName = (TextView) itemView.findViewById(R.id.chat_name);
            this.cTime = (TextView) itemView.findViewById(R.id.chat_last_message_time);
            this.cLastMessage = (TextView) itemView.findViewById(R.id.chat_last_message);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        Viewholder holder;
        View view = View.inflate(parent.getContext(), R.layout.item_chat, null);
        holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.cName.setText(mDataList.get(position).getChatName());
        holder.cTime.setText(mDataList.get(position).getChatTime());
        holder.cLastMessage.setText(mDataList.get(position).getChatLastMessage());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}