package com.makezhao.rongcloudtest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by MakeZhao on 17/4/23.
 */

public class HomeActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;//将Tab页面持久在内存中
    private Fragment mConversationList;
    private Fragment mConversationFragment = null;
    private List<Fragment> mFragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        mConversationList = initConversationList();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mFragments.add(mConversationList);
        mFragments.add(FriendFragment.getInstance());
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    private Fragment initConversationList() {
        if (mConversationFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "flase") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "flase")
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "flase")//系统
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return mConversationFragment;
        }
    }
}
