package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import java.sql.Date;
import java.util.Calendar;

public class Utils {

    public static java.sql.Date getDateTime() {
        return new Date(Calendar.getInstance().getTimeInMillis());
    }

    public static java.sql.Date getYesterday(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
        return new Date(cal.getTimeInMillis());
    }
}
