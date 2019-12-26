package id.ac.ui.cs.mobileprogramming.hanifa.odoj.utils;

import java.sql.Date;
import java.util.Calendar;

public class Utils {

    public static java.sql.Date getDateTime() {
        return new Date(Calendar.getInstance().getTimeInMillis());
    }

}
