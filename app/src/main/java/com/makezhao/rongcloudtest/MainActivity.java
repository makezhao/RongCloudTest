package com.makezhao.rongcloudtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends Activity implements View.OnClickListener, RongIM.UserInfoProvider {
    private static final String TAG = "MainActivity";
    private List<Friend> userIdList;
    private static final String token1 = "9ww4N5evlRdzT1sY2AcPzr3fPqwQdQbVLKDZW4x2OkFjbwlYbG8BLnsZCVNRalF9qIUUWTtAcyo/Nx0ivTY7lw==";
    private static final String token2 = "MtWRBPFtezW0Nv5pXF0wuWdWOsBBrkSp/UmBWv/F6NdI7lt3mdILLtBeba6pnnBhtktbMf1/vpouSQ79T17kRg==";
    private Button mUser1, mUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUser1 = (Button) findViewById(R.id.connect_10010);
        mUser2 = (Button) findViewById(R.id.connect_10086);
        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);

        initUserInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect_10010:
                connectRongServer(token1);
                break;
            case R.id.connect_10086:
                connectRongServer(token2);
                break;
            default:
                break;
        }
    }

    private void connectRongServer(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "token is error,please check token and appkey");
            }

            @Override
            public void onSuccess(String userId) {
                if (userId.equals("10010")) {
                    mUser1.setText("用户1连接服务器成功");
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 10010", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    Toast.makeText(MainActivity.this, "connect server success 10086", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "connect failure,errorCode is:" + errorCode.getValue());
            }
        });
    }

    private void initUserInfo() {
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend("10010", "fun", "http://img5.duitang.com/uploads/item/201508/24/20150824065409_ckw5a.thumb.224_0.jpeg"));
        userIdList.add(new Friend("10086", "make", "http://tx.haiqq.com/uploads/allimg/c161103/14N10U60I040-IY2.jpg"));

        RongIM.setUserInfoProvider(this, true);
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.d(TAG, i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        Log.d(TAG, "userId is:" + s);
        return null;
    }
}
