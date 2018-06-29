package com.shakeel.testgithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shakeel.testgithub.adapter.FollowerListAdapter;
import com.shakeel.testgithub.models.Follower;
import com.shakeel.testgithub.models.User;
import com.shakeel.testgithub.networking.RetrofitClient;
import com.shakeel.testgithub.utils.LoadingDialog;
import com.shakeel.testgithub.utils.MyDialog;
import com.shakeel.testgithub.utils.Utility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity {
    private List<Follower> followersList = new ArrayList<>();
    private User user;
    private RecyclerView follwersRecyclerView;
    private FollowerListAdapter adapter;
    private TextView userNameTv, userEmailTv, followersTextMsg;
    private ImageView userImage;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.user_detail_label));
        user = (User) getIntent().getSerializableExtra(Utility.OBJECT_EXTRAS);
        dialog = new LoadingDialog(UserDetailActivity.this);

        // Inflating the UI Components
        userEmailTv = findViewById(R.id.userEmail);
        userImage = findViewById(R.id.userImage);
        userNameTv = findViewById(R.id.userName);
        followersTextMsg = findViewById(R.id.followers_txt_msg);
        follwersRecyclerView = findViewById(R.id.followersRecyclerView);
        follwersRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(UserDetailActivity.this, 2);
        follwersRecyclerView.setLayoutManager(layoutManager);


        // Setting the values of User Detail
        userNameTv.setText(user.getName());
        Picasso.get().load(user.getAvatarUrl()).into(userImage);
        if (user.getEmail() != null) {
            userEmailTv.setText(user.getEmail().toString());
        } else {
            userEmailTv.setText("Null");
        }

        getFollowersList(user.getLogin());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getFollowersList(String userName) {
        if (Utility.isNetworkAvailable(UserDetailActivity.this)) {
            dialog.show();
            Call<List<Follower>> followerListCall = RetrofitClient.getInstance().getApiService().getFollower(Utility.getUserHeader(), userName);
            followerListCall.enqueue(new Callback<List<Follower>>() {
                @Override
                public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                        dialog.dismiss();
                        followersList = response.body();
                        if (followersList != null) {
                            adapter = new FollowerListAdapter(followersList);
                            follwersRecyclerView.setAdapter(adapter);
                            adapter.dataSetChanged(followersList);
                        }else {
                           followersTextMsg.setText(getResources().getString(R.string.followers_not_found));
                        }

                    } else {
                        dialog.dismiss();
                        MyDialog.showDialog(UserDetailActivity.this, getResources().getString(R.string.invalid_email_error_title), response.message(), null);
                    }
                }

                @Override
                public void onFailure(Call<List<Follower>> call, Throwable t) {
                    dialog.dismiss();
                    MyDialog.showDialog(UserDetailActivity.this, getResources().getString(R.string.invalid_email_error_title), t.toString(), null);
                }
            });
        } else {
            MyDialog.showDialog(UserDetailActivity.this, getResources().getString(R.string.network_error_title), getResources().getString(R.string.network_error_msg), null);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}

