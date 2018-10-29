package com.shakeel.testgithub.view;

import com.shakeel.testgithub.models.Follower;

import java.util.List;

public interface UserDetailActivityView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showImageLoadingProgress();

    void showErrorDialog(String error);

    void showNetworkErrorDialog();

    void notifyRecyclerView(List<Follower> followers);

    void followersNotFound();
}
