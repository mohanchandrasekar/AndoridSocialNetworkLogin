package com.mohan.andoridsocialnetworklogin;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mohan.andoridsocialnetworklogin.Manager.AlertDialogManager;
import com.mohan.andoridsocialnetworklogin.Manager.LoginSessionManager;

import java.util.HashMap;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    TextView mPassWord;
    private AlertDialogManager mAlertDialogManager;
    private LoginSessionManager mLoginSessionManager;
    private Button mLoginButton;
    private TextView mUserName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initUI();

        mLoginSessionManager.checkLogin();

        HashMap<String, String> user = mLoginSessionManager.getUserDetails();

        String name = user.get(LoginSessionManager.KEY_NAME);

        String email = user.get(LoginSessionManager.KEY_EMAIL);

        mUserName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        mPassWord.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

        actionUI();
    }

    private void actionUI() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mLoginSessionManager.logoutUser();
            }
        });
    }

    private void initUI() {
        mUserName = findViewById(R.id.user_name);
        mPassWord = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.logout);
    }

    private void init() {
        mAlertDialogManager = new AlertDialogManager();
        mLoginSessionManager = new LoginSessionManager(getApplicationContext());

    }

}