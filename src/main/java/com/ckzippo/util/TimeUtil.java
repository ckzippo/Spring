package com.ckzippo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IDEA
 * USER:ckzippo
 * Date:17/2/16
 * TIME:上午9:04
 */
public class TimeUtil {
    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(TimeUtil.getCurrentTime());
    }
}
