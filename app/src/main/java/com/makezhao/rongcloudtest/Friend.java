package com.makezhao.rongcloudtest;

import static io.rong.imlib.statistics.UserData.name;

/**
 * Created by MakeZhao on 17/4/23.
 */

public class Friend {
    private String userId;
    private String userName;
    private String portraitUri;

    public Friend(String userId, String userName, String portraitUri) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.portraitUri = portraitUri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = name;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
