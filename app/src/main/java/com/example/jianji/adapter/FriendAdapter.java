package com.example.jianji.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jianji.Chat.Chat_friend;
import com.example.jianji.DB.Myuser;
import com.example.jianji.R;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context mContext;
    private List<Chat_friend> friends;

    public FriendAdapter(List<Chat_friend> Chat_friends){
        this.friends = Chat_friends;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.friend_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat_friend friend = friends.get(position);
        holder.fiendName.setText(friend.getFiendName());
        holder.latestMes.setText(friend.getLatestMes());
        Glide.with(mContext).load(friend.getAvatar()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fiendName,latestMes;
        ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fiendName = itemView.findViewById(R.id.friendName_text);
            latestMes = itemView.findViewById(R.id.latestMes_text);
            avatar = itemView.findViewById(R.id.avatar_img);
        }
    }

}
