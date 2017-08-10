package com.cwlm.capacitylock.model;

import com.cwlm.capacitylock.obj.UserObj;

/**
 * Created by Zheng on 2017/8/10.
 */

public class UserModel extends BaseModel{
    private UserObj object;

    public UserObj getObject() {
        return object;
    }

    public void setObject(UserObj object) {
        this.object = object;
    }
}
