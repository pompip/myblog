package cn.pompip.mybill.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static Calendar calendar;
    static class Inner{
        static DateUtil dateUtil = new DateUtil();

    }


    public static DateUtil instance(Date date) {
        if (calendar == null) {
            calendar = Calendar.getInstance(Locale.CHINA);
        }
        calendar.setTime(date);
        return Inner.dateUtil;
    }

    public static DateUtil instance() {
        return instance(new Date());
    }

    private DateUtil( ) {
    }

    public int getMonth(){
        return calendar.get(Calendar.MONTH) +1;
    }

    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }
}
