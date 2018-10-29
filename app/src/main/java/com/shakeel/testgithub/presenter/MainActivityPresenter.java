package com.shakeel.testgithub.presenter;

import android.content.Context;

import com.shakeel.testgithub.R;
import com.shakeel.testgithub.models.User;
import com.shakeel.testgithub.networking.RetrofitClient;
import com.shakeel.testgithub.utils.Utility;
import com.shakeel.testgithub.view.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {
    private MainActivityView view;
    private User user;
    private Context mContext;

    public MainActivityPresenter(MainActivityView view, Context context) {
        this.view = view;
        this.mContext = context;
    }

    public void searchUser(String email) {
        if (Utility.isNetworkAvailable(mContext)) {

            user = null;
            view.showLoadingDialog();
            Call<User> searchUserCall = RetrofitClient.getInstance().getApiService().getUser(Utility.getUserHeader(), email);
            searchUserCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                        view.hideLoadingDialog();
                        user = response.body();
                        view.showAndUpdateUser(user);
                        view.showImageLoadingProgress();

                    } else {
                        view.hideLoadingDialog();
                        view.showErrorDialog(mContext.getResources().getString(R.string.invalid_email_error_title));

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    view.hideLoadingDialog();
                    view.showErrorDialog(t.toString());
                }
            });
        } else {
            view.showNetworkErrorDialog();
        }
    }

}

