package com.ckzippo.Enum;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:9/7/16
 * TIME:3:26 PM
 */
public enum HTTPEnum {

    QRYUSER("qry?cmd=306", 1), //查询用户
    MODUSER("mod?cmd=312", 2), //修改用户
    QRYDEPT("lowi?cmd=311", 3), //查询部门
    ADDUSER("mod?cmd=327", 4), //新增用户
    GRANTUSER("mod?cmd=312",5), //为用户赋予权限
    RESETPW("mod?cmd=312",6),  //重置用户密码
    ADDUSERDEPT("lowi?cmd=354",7), // 新增成员和组织架构关系
    DELUSER("mod?cmd=328", 8),  // 删除用户
    QRYGROUP("qry?cmd=310", 9), //查询群列表
    MODGROUP("mod?cmd=317", 10), //修改群信息
    QRYGROUPMEM("qry?cmd=308", 11), // 查看群成员
    ADDGROUPMEM("mod?cmd=318", 12), //增加群成员
    DELGROUPMEM("mod?cmd=319", 13),  // 删除群成员
    DELGROUP("mod?cmd=316", 14)     // 删除群
    ;

    private static final String URL = "http://yx.telecomjs.com:7500/";

    private String CMD;
    private int index;

    HTTPEnum(String CMD, int index) {
        this.CMD = CMD;
        this.index = index;
    }

    /**
     * Returns the name of this enum constant, as contained in the
     * declaration.  This method may be overridden, though it typically
     * isn't necessary or desirable.  An enum type should override this
     * method when a more "programmer-friendly" string form exists.
     *
     * @return the name of this enum constant
     */
    @Override
    public String toString() {
        return URL + getCMD();
    }

    public String getCMD() {
        return CMD;
    }

    public void setCMD(String CMD) {
        this.CMD = CMD;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
