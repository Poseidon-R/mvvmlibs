package com.robot.baselibs.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AD on 2016/5/1.
 * Edited by Fred on 2016/11/8
 * 时间格式转换工具
 */
public class TimeUtil {
    public static long talkingtime;

    public final static String DATEFORMATER = "yyyy-MM-dd HH:mm:ss";
    public final static String DATEFORMATER2 = "yyyy-MM-dd";
    public final static String DATEFORMATER3 = "HH:mm:ss";
    public final static String DATEFORMATER4 = "MM-dd HH:mm";
    public final static String DATEFORMATER5 = "yyyy/MM/dd";
    public final static String DATEFORMATER6 = "HH:mm";
    public final static String DATEFORMATER7 = "yy/MM/dd";
    public final static String DATEFORMATER8 = "MM-dd HH:mm:ss";

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER2);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER3);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater4 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER4);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater5 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER5);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormaterHM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER6);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> dateFormater8 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATEFORMATER8);
        }
    };
    public static String getTime(long timestamp, String type) {
        Date date = new Date(timestamp * 1000);
        String dateTime = null;

        switch (type) {
            case DATEFORMATER:
                dateTime = dateFormater.get().format(date);
                break;
            case DATEFORMATER2:
                dateTime = dateFormater2.get().format(date);
                break;
            case DATEFORMATER3:
                dateTime = dateFormater3.get().format(date);
                break;
            case DATEFORMATER4:
                dateTime = dateFormater4.get().format(date);
                break;
            case DATEFORMATER5:
                dateTime = dateFormater5.get().format(date);
                break;
            case DATEFORMATER6:
                dateTime = dateFormaterHM.get().format(date);
                break;
            case DATEFORMATER7:
                String format = dateFormater5.get().format(date);
                dateTime = format.substring(2, format.length());
                break;
            default:

        }

        return dateTime;
    }


    public static String getTimeMS(long endTime) {
        Date date = new Date(endTime);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        String runTime = formatter.format(date);
        return runTime;
    }

    public static String getTimeMZHS(long endTime) {
        Date date = new Date(endTime);
        SimpleDateFormat formatter = new SimpleDateFormat("mm分ss秒");
        String runTime = formatter.format(date);
        return runTime;
    }

    public static String getRuntime(long endTime) {

        Date date = new Date(endTime);
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        String runTime = formatter.format(date);
        runTime = runTime.substring(0, runTime.length() - 1);
        return runTime;
    }

    public static String getMediaDurtionTime(long time) {//
        long secondTime = time;
        int hour = (int) (secondTime / 3600);
        int minute = (int) (secondTime % 3600);
        if (minute != 0) {
            minute = minute / 60;
        }
        int second = (int) (secondTime % 60);
        String houstr = null;
        String minuteStr = null;
        String secondStr = null;
        if (hour < 10) {
            houstr = "0" + hour;
        } else {
            houstr = String.valueOf(hour);
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        } else {
            minuteStr = String.valueOf(minute);
        }

        if (second < 10) {
            secondStr = "0" + second;
        } else {
            secondStr = String.valueOf(second);
        }

        String endtime = null;
        /*if (houstr.equals("00")) {//只有时位为0才输出分位
            endtime = minuteStr + ":" + secondStr;
        } else {//默认最高位是时位
            endtime = houstr + ":" + minuteStr + ":" + secondStr;
        }*/

        endtime = houstr + ":" + minuteStr + ":" + secondStr;

        return endtime;
    }

    //把时间转化为时间戳
    public static long getTime(String timeString) {
        long timeStamp = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d;
        try {
            d = sdf.parse(timeString);
            timeStamp = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    //把环信时间转化为秒
    public static long getEaseMobTime(String timeString) {

        String[] time = timeString.split(":");

        long timeStamp = 0L;
        //最后一次取秒数
        timeStamp = Long.valueOf(time[1]);

       /*  SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        Date d;
        try {
            d = sdf.parse(timeString);
            timeStamp = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        return timeStamp;
    }


    static String result1 = "";

    //时间戳字符串转Long 时间戳 *1000变成毫秒
    public static long getTimeStamp(String timestamp) {
        long t = (long) (Double.parseDouble(timestamp) * 1000L);
        return t;
    }

    //时间戳字符串转Long 时间戳 *1000变成毫秒
    public static long getTimeStamp(Long timestamp) {
        long t = timestamp * 1000L;
        return t;
    }

    public static long getTimeStamp(long timestamp) {
        long t = timestamp * 1000L;
        return t;
    }

    //由时间戳转成年月日 分时秒
    public static String getDateYMDHMS(long timestamp) {
        Date date = new Date(timestamp * 1000);
        String dateTime = dateFormater.get().format(date);
        return dateTime;
    }


    public static String getDateYMD(long timestamp) {
        Date date = new Date(timestamp * 1000);
        String dateTime = dateFormater2.get().format(date);
        return dateTime;
    }

    public static String getDateYMD1(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormater2.get().format(date);
        return dateTime;
    }

    //由时间戳转成月日 分时
    public static String getDateMDHM(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormater4.get().format(date);
        String[] dateString = dateTime.split("-");
        String dateLast = dateString[0] + "月" + dateString[1].substring(0, 2) + "日 " + dateString[1].substring(3);
        return dateLast;
    }
    //由时间戳转成月日 分时
    public static String getDateMDHMS(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormater8.get().format(date);
        String[] dateString = dateTime.split("-");
        String dateLast = dateString[0] + "月" + dateString[1].substring(0, 2) + "日 " + dateString[1].substring(3);
        return dateLast;
    }

    //由时间戳转成分时秒
    public static String getDgetDateHMS(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormater3.get().format(date);
        return dateTime;
    }

    //由时间戳转成分时
    public static String getDgetDateHM(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormaterHM.get().format(date);
        return dateTime;
    }

    //由时间戳转成分时秒
    public static String getDgetDateHMSZh(long timestamp) {
        Date date = new Date(timestamp);
        String dateTime = dateFormater3.get().format(date);
        String[] dateString = dateTime.split(":");
        String dateLast = dateString[0] + "小时" + dateString[1] + "分 " + dateString[2] + "秒";
        return dateLast;
    }

    public static String getDgetDateHMSZh(String dateTime) {
        String[] dateString = dateTime.split(":");

        String dateLast = dateString[0] + "小时" + dateString[1] + "分 " + dateString[2] + "秒";
        if (Integer.valueOf(dateString[0]) > 24) {
            dateLast = Integer.valueOf(dateString[0]) / 24 + "天" +
                    Integer.valueOf(dateString[0]) % 24 + "小时" + dateString[1] + "分 " + dateString[2] + "秒";
        }
        return dateLast;
    }

    //把时间戳转化为友好时间
    public static String friendly_time(long timestamp) {
        //获取time距离当前的秒数
        int ct = (int) ((System.currentTimeMillis() - timestamp) / 1000);

        if (ct == 0) {
            return "刚刚";
        }

        if (ct > 0 && ct < 60) {
            return ct + "秒前";
        }

        if (ct >= 60 && ct < 3600) {
            return Math.max(ct / 60, 1) + "分钟前";
        }
        if (ct >= 3600 && ct < 86400)
            return ct / 3600 + "小时前";
        if (ct >= 86400 && ct < 2592000) { //86400 * 30
            int day = ct / 86400;
            return day + "天前";
        }
        if (ct >= 2592000 && ct < 31104000) { //86400 * 30
            return ct / 2592000 + "月前";
        }
        return ct / 31104000 + "年前";
    }

    /**
     * 转化为剩余时间
     */
    public static String getLeftTime(long distance) {

        if (distance >= 31104000) {
            distance = distance / 31104000;
            result1 += String.valueOf(distance) + "年";
        } else if (distance >= 2592000) {
            distance = distance / 2592000;
            result1 += String.valueOf(distance) + "月";
        } else if (distance >= 86400) {
            distance = distance / 86400;
            result1 += String.valueOf(distance) + "天";
        } else if (distance >= 3600) {
            distance = distance / 3600;
            result1 += String.valueOf(distance) + "小时";
        } else if (distance >= 60) {
            distance = distance / 60;
            result1 += String.valueOf(distance) + "分";
        } else if (distance >= 1) {
            result1 += String.valueOf(distance) + "秒";
        } else {
            return result1;
        }
        getLeftTime(distance);
        return result1;
    }

    /**
     * 将时间字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将yyyy-MM-dd"字符串转为秒级时间戳
     *
     * @param
     * @return
     */
    public static String date2TimeStamp(String date_str) {
        return date2TimeStamp(date_str, "yyyy-MM-dd");
    }


    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static long date2TimeStampLong(String date_str, String format, boolean ismill) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (ismill) {
                //毫秒级
                return sdf.parse(date_str).getTime();
            } else {
                //秒级
                return sdf.parse(date_str).getTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String timeStamp2Date(String seconds) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    public static String getFgtime(String time) {
        if (StringUtil.isValid(time)&&time.indexOf(" ")!=-1) {
            return time.split(" ")[0];
        }else {
            return time;
        }
    }


}
