package com.shakeel.testgithub.presenter;

import android.content.Context;

import com.shakeel.testgithub.R;
import com.shakeel.testgithub.UserDetailActivity;
import com.shakeel.testgithub.adapter.FollowerListAdapter;
import com.shakeel.testgithub.models.Follower;
import com.shakeel.testgithub.models.User;
import com.shakeel.testgithub.networking.RetrofitClient;
import com.shakeel.testgithub.utils.MyDialog;
import com.shakeel.testgithub.utils.Utility;
import com.shakeel.testgithub.view.MainActivityView;
import com.shakeel.testgithub.view.UserDetailActivityView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivityPresenter {
    private UserDetailActivityView view;
    private List<Follower> followers;
    private Context mContext;

    public UserDetailActivityPresenter(UserDetailActivityView view, Context context) {
        this.view = view;
        this.mContext = context;
        followers = new ArrayList<>();
    }

    public void getFollowersList(String userName) {
        if (Utility.isNetworkAvailable(mContext)) {
            view.showLoadingDialog();
            Call<List<Follower>> followerListCall = RetrofitClient.getInstance().getApiService().getFollower(Utility.getUserHeader(), userName);
            followerListCall.enqueue(new Callback<List<Follower>>() {
                @Override
                public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                       view.hideLoadingDialog();
                        followers = response.body();
                        if (followers != null) {
                           view.notifyRecyclerView(followers);
                        }else {
                            view.followersNotFound();
                        }

                    } else {
                        view.hideLoadingDialog();
                        view.showErrorDialog(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Follower>> call, Throwable t) {
                    view.hideLoadingDialog();
                    view.showErrorDialog(t.toString());

                }
            });
        } else {
            view.showNetworkErrorDialog();
        }
    }


}

