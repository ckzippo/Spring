package com.ckzippo.groupmanage;

import com.google.common.base.MoreObjects;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/27
 * TIME:上午9:05
 */
public class GroupMember {
    private String groupid;
    private String memberid;
    private String membername;
    private String sex;
    private String grouprole;
    private String telephone;
    private String mobilephone;
    private String email;

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGrouprole() {
        return grouprole;
    }

    public void setGrouprole(String grouprole) {
        this.grouprole = grouprole;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("groupid", groupid)
                .add("memberid", memberid)
                .add("membername", membername)
                .add("sex", sex)
                .add("grouprole", grouprole)
                .add("telephone", telephone)
                .add("mobilephone", mobilephone)
                .add("email", email)
                .toString();
    }
}
