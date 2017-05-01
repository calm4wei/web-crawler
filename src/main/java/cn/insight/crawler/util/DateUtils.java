package cn.insight.crawler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fengwei on 17/5/1.
 */
public class DateUtils {


    public static String completeDate(String publish_date) {
        String date = "";
        if (publish_date != null && !"".equals(publish_date)) {
            date = publish_date.substring(0, publish_date.indexOf("-") + 3);
            date = getYear() + "-" + date;
        }
        return date;

    }

    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static String formatDate(String time) {
        String re = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            re = sdf.format(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return re;
    }
}
