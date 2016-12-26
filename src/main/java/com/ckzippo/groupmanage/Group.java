package com.ckzippo.groupmanage;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:16/12/23
 * TIME:下午3:01
 */

import com.google.common.base.MoreObjects;

/**
 * 群组实体类
 */
public class Group {
    private int id;
    private String gp_name;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGp_name() {
        return gp_name;
    }

    public void setGp_name(String gp_name) {
        this.gp_name = gp_name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("gp_name", gp_name)
                .add("note", note)
                .toString();
    }
}
