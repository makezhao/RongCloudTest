package com.makezhao.rongcloudtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.rong.imkit.RongIM;

/**
 * Created by MakeZhao on 17/4/23.
 */

public class FriendFragment extends Fragment {
    public static FriendFragment instance = null;

    public static FriendFragment getInstance() {
        if (instance == null) {
            instance = new FriendFragment();
        }
        return instance;
    }

    private View mView;
    private Button mButton1, mButton2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.friend_fragment, null);
        mButton1 = (Button) mView.findViewById(R.id.friend1);
        mButton2 = (Button) mView.findViewById(R.id.friend2);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(getContext(), "10086", "私人聊天");
                }
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(getContext(), "10010", "私人聊天");
                }
            }
        });
        return mView;
    }
}
