package com.example.expressdeliverysystemserver.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    /**
     * 时间生成函数
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfTime.format(new Date());
    }

    /**
     * 处理时间函数
     *
     * @return
     */
    public static String getTimeHandle() {
        return getTime().replaceAll("[[\\s-:punct:]]", "");
    }
}
