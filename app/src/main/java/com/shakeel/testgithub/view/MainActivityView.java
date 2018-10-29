package com.shakeel.testgithub.view;

import com.shakeel.testgithub.models.User;

public interface MainActivityView {
    void showLoadingDialog();
    void hideLoadingDialog();
    void showAndUpdateUser(User user);
    void showImageLoadingProgress();
    void showErrorDialog(String error);
    void showNetworkErrorDialog();
}
