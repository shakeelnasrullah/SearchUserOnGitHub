package com.shakeel.testgithub;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shakeel.testgithub.models.User;
import com.shakeel.testgithub.networking.RetrofitClient;
import com.shakeel.testgithub.utils.LoadingDialog;
import com.shakeel.testgithub.utils.MyDialog;
import com.shakeel.testgithub.utils.Utility;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSearchActivity extends AppCompatActivity {

    private TextInputEditText searchEditText;
    private Button search_without_auth_btn;
    private ImageView userImage;
    private TextView userName;
    private CardView userProfileLayout;
    private User user;
    private LoadingDialog dialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
        getSupportActionBar().setTitle(getResources().getString(R.string.search_user_label));

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
                    searchUserByEmail(searchEditText.getText().toString());
                } else {
                    MyDialog.showDialog(UserSearchActivity.this, getResources().getString(R.string.invalid_email_error_title), getResources().getString(R.string.empty_error_msg), null);
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

    private void searchUserByEmail(String email) {
        if (Utility.isNetworkAvailable(UserSearchActivity.this)) {

            user = null;
            dialog.show();
            Call<User> searchUserCall = RetrofitClient.getInstance().getApiService().getUser(Utility.getUserHeader(), email);
            searchUserCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                        dialog.dismiss();
                        user = response.body();
                        userProfileLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
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
                        userName.setText(user.getName());

                    } else {
                        dialog.dismiss();
                        MyDialog.showDialog(UserSearchActivity.this, getResources().getString(R.string.invalid_email_error_title), response.message(), null);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    dialog.dismiss();
                    MyDialog.showDialog(UserSearchActivity.this, getResources().getString(R.string.invalid_email_error_title), t.toString(), null);
                }
            });
        } else {
            MyDialog.showDialog(this, getResources().getString(R.string.network_error_title), getResources().getString(R.string.network_error_msg), null);
        }
    }

}
