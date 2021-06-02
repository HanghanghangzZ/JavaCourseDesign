package com.hang.toilethrmanagement.utils;

public class TimestampDiff {
    public static String diffToTimeStamp(Long diff) {
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        return (days + "天" + hours + "小时" + minutes + "分");
    }
}
