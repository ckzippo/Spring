package com.ckzippo.dgpmanage;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/29
 * TIME:上午10:58
 */

import com.google.common.base.MoreObjects;

/**
 * 讨论组实体类
 */
public class DGroup {
    private String dgpid;
    private String dgpname;

    public String getDgpid() {
        return dgpid;
    }

    public void setDgpid(String dgpid) {
        this.dgpid = dgpid;
    }

    public String getDgpname() {
        return dgpname;
    }

    public void setDgpname(String dgpname) {
        this.dgpname = dgpname;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dgpid", dgpid)
                .add("dgpname", dgpname)
                .toString();
    }
}
