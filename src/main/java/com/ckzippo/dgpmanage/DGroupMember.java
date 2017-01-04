package com.ckzippo.dgpmanage;

import com.google.common.base.MoreObjects;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/29
 * TIME:下午2:51
 */
public class DGroupMember {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String dgpid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDgpid() {
        return dgpid;
    }

    public void setDgpid(String dgpid) {
        this.dgpid = dgpid;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("dpgid", dgpid)
                .toString();
    }
}
