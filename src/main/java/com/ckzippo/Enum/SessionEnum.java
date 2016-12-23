package com.ckzippo.Enum;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:9/6/16
 * TIME:9:19 PM
 */
public enum SessionEnum {
    USERNAME("username", 1), //用户名
    PASSWORD("password", 2), //密码
    AUTHENTICATION("authentication",3), //是否认证
    QRYUSER("qryuser",4),//存储查询到的用户
    DEPTID("deptid", 5), //管理员能够修改的最高部门ID
    ADUSERDEPTPATH("deptpath", 6), //添加成员时的部门路径
    LASTDEPTID("lastdeptid", 7), //添加成员时的上一个部门节点
    ;

    private String sessionName;
    private int index;

    SessionEnum(String sessionName, int index) {
        this.sessionName = sessionName;
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
        return getSessionName();
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
