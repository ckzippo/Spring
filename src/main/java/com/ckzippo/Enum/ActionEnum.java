package com.ckzippo.Enum;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:9/6/16
 * TIME:8:57 PM
 */
public enum ActionEnum {
    /**
     * 所有的ACTION值
     */
    LOGIN("login",1),
    QRYUSER("qryuser", 2),
    MODUSER("modifyuser", 3),
    ADDUSER("adduser", 4),
    QRYGROUP("qrygroup", 5),
    MODGROUP("modgroup", 6),
    QRYGRPMEM("qrygroupmem", 7),
    QRYDGROUP("qrydgroup", 8),
    QRYDGPMEM("qrydgoupmem", 9)
    ;

    private String actionName;
    private int index;

    ActionEnum(String actionName, int index) {
        this.actionName = actionName;
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
        return getActionName();
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
