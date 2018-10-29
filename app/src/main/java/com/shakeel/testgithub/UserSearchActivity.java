package com.shakeel.testgithub;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shakeel.testgithub.models.User;
import com.shakeel.testgithub.networking.RetrofitClient;
import com.shakeel.testgithub.presenter.MainActivityPresenter;
import com.shakeel.testgithub.utils.LoadingDialog;
import com.shakeel.testgithub.utils.MyDialog;
import com.shakeel.testgithub.utils.Utility;
import com.shakeel.testgithub.view.MainActivityView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchActivity extends AppCompatActivity implements MainActivityView {

    private TextInputEditText searchEditText;
    private Button search_without_auth_btn;
    private ImageView userImage;
    private TextView userName;
    private CardView userProfileLayout;
    private User user;
    private LoadingDialog dialog;
    private ProgressBar progressBar;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        getSupportActionBar().setTitle(getResources().getString(R.string.search_user_label));

        presenter = new MainActivityPresenter(this, UserSearchActivity.this);
        dialog = new LoadingDialog(UserSearchActivity.this);
        searchEditText = findViewById(R.id.email_edit_text);
        search_without_auth_btn = findViewById(R.id.search_with_out_auth);
        userImage = findViewById(R.id.user_image);
        userName = findViewById(R.id.user_Name);
        userProfileLayout = findViewById(R.id.user_layout);
        progressBar = findViewById(R.id.prgressBar);
        progressBar.setVisibility(View.GONE);

        // User name and picture will not be visible before api success response
        userProfileLayout.setVisibility(View.GONE);

        search_without_auth_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideKeyboard(UserSearchActivity.this);
                if (Utility.isEmpty(searchEditText.getText())) {
                    presenter.searchUser(searchEditText.getText().toString());
                    searchEditText.setText("");
                } else {
                    MyDialog.showDialog(UserSearchActivity.this, getResources().getString(R.string.invalid_email_error_title), getResources().getString(R.string.empty_error_msg), null);
                    searchEditText.setText("");
                }
            }
        });
        userProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Utility.OBJECT_EXTRAS, (Serializable) user);
                    Utility.launchActivity(UserSearchActivity.this, UserDetailActivity.class, bundle);
                }
            }
        });

    }

    @Override
    public void showLoadingDialog() {
        dialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        dialog.hide();
    }

    @Override
    public void showAndUpdateUser(User user) {
        this.user = user;
        userProfileLayout.setVisibility(View.VISIBLE);
        userImage.setVisibility(View.VISIBLE);
        userName.setText(user.getName());
        Picasso.get().load(user.getAvatarUrl()).into(userImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showImageLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorDialog(String error) {
        MyDialog.showDialog(this, getResources().getString(R.string.error_dialog_title), error, null);
    }

    @Override
    public void showNetworkErrorDialog() {
        MyDialog.showDialog(this, getResources().getString(R.string.network_error_title), getResources().getString(R.string.network_error_msg), null);
    }

}
