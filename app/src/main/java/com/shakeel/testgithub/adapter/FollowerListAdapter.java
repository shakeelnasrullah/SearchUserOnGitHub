package com.shakeel.testgithub.adapter;

import android.media.tv.TvContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shakeel.testgithub.R;
import com.shakeel.testgithub.models.Follower;
import com.shakeel.testgithub.models.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shakeel on 6/298/2018.
 */

public class FollowerListAdapter extends RecyclerView.Adapter<FollowerListAdapter.MyViewHolder> {

    private List<Follower> userList;


    public FollowerListAdapter(List<Follower> userList) {
        this.userList = userList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        ProgressBar progressBar;

        MyViewHolder(View view) {
            super(view);
            userImage = (ImageView) view.findViewById(R.id.userImage);
            userName = (TextView) view.findViewById(R.id.userName);
            progressBar = (ProgressBar) view.findViewById(R.id.prgressBar);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_list_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final  MyViewHolder holder, int position) {
        //Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(list.get(position).getUrl()), THUMBSIZE, THUMBSIZE);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.get().load(userList.get(holder.getAdapterPosition()).getAvatarUrl()).resize(200, 200).into(holder.userImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.GONE);

            }
        });
        holder.userName.setText(userList.get(position).getLogin());


    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void dataSetChanged(List<Follower> list) {
        this.userList = list;
        notifyDataSetChanged();

    }

}
