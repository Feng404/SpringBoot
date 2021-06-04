package com.base.modules.sys.entity;

import com.base.common.BaseEntity;

/**
 * ClassName  SysUserEntity
 * Description    用户
 * Author F
 * Date   2021/6/3 13:48
 * Version    1.0
 */
public class SysUserEntity extends BaseEntity {
    /**
     * 姓名
     */
    private String userName;
    /**
     * No
     */
    private String userNo;
    /**
     * 性别
     */
    private String userGender;
    /**
     * 机构id
     */
    private String orgId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
