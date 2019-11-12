package com.shop.manager.util;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @Author: Young
 * @Description:
 * @Date: 2019/7/12 0012 13:57
 */
public class TimeUtil {

    public static LocalDateTime timeZone2Data(Long timeStamp, Integer timeZone) {
        return LocalDateTime.ofInstant(new Date(Long.parseLong(String.valueOf(timeStamp)))
                .toInstant(), ZoneId.of(timeZone > 0 ? ("+" + timeZone) : timeZone + ""));
    }

    /**
     * timeZone : 时区
     * dateTime : 时间戳到毫秒
     *
     * @param dateTime
     * @param timeZone
     */
    public static Long TimeZoneConversion(String dateTime, String timeZone) {
        SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置时区
        londonSdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        String changeTime = londonSdf.format(stampToDate(dateTime));

        return stampToTime(changeTime);
    }

    /**
     * 将时间戳转换为时间对象
     */
    public static Date stampToDate(String s) {
        long lt = new Long(s);
        Date date = new Date(lt);
        return date;
    }

    /**
     * 将时间戳转换为时间字符串 只取年月
     */
    public static String getYearAndMonth(String timeStamp) {
        return timeStamp2Date(timeStamp, "yyyy-MM-dd");
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 将字符串时间类型转时间戳
     *
     * @param time
     * @return
     */
    public static long stampToTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
        }
        return 0;
    }

    /**
     * 获取前天零点
     *
     * @param
     * @return
     */
    public static long dayBeforeYesterdayMin() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MIN).minusDays(2);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取前天23:59:59时间戳
     *
     * @param
     * @return
     */
    public static long dayBeforeYesterdayMax() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MAX).minusDays(2);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取昨天零点时间戳
     *
     * @param
     * @return
     */
    public static long yesterdayMinTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MIN).minusDays(1);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 昨天天23:59:59时间戳
     *
     * @param
     * @return
     */
    public static long yesterdayMaxTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MAX).minusDays(1);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 获取今天23点时间戳
     *
     * @param
     * @return
     */
    public static long todayMaxTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MAX);//当天23:59:59时间戳
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取今天0点时间戳
     *
     * @param
     * @return
     */
    public static long todayMinTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MIN);//当天0时间戳
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取明天23点时间戳
     *
     * @param
     * @return
     */
    public static long tomorrowMaxTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MAX).plusDays(1);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取明天0点时间戳
     *
     * @param
     * @return
     */
    public static long tomorrowMinTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MIN).plusDays(1);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取7天前0点时间戳
     *
     * @param
     * @return
     */
    public static long weekMaxTimeStamp() {
        LocalDateTime data = LocalDateTime.of(java.time.LocalDate.now(), LocalTime.MAX).plusDays(7);
        return data.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 获取两个日期中间所有时间集合
     * 格式：时间戳
     *
     * @param startTime
     * @param endTime
     * @return 时间戳
     */
    public static List<String> getDays(String startTime, String endTime) {

        startTime = TimeUtil.timeStamp2Date(startTime, null);
        endTime = TimeUtil.timeStamp2Date(endTime, null);
        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // 日期加1(包含结束)
            tempEnd.add(Calendar.DATE, +1);
            while (tempStart.before(tempEnd)) {
                days.add(String.valueOf(TimeUtil.stampToTime(dateFormat.format(tempStart.getTime()))));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取两个日期中间所有时间集合
     * 格式：2018-01-05
     *
     * @param startTime
     * @param endTime
     * @return 时间字符串
     */
    public static List<String> getDayScope(String startTime, String endTime, String format) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            // 日期加1(包含结束)
            tempEnd.add(Calendar.DATE, +1);
            while (tempStart.before(tempEnd)) {
                days.add(String.valueOf(dateFormat.format(tempStart.getTime())));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

}
