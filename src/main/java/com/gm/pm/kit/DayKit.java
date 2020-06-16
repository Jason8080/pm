package com.gm.pm.kit;

import java.util.Date;

/**
 * @author Jason
 */
public class DayKit {


    public static long getDiffDays(Date date, long time) {
        if (date != null) {
            return getDiffDays(date.getTime(), time);
        }
        return 0;
    }

    public static long getDiffDays(long time, Date date) {
        if (date != null) {
            return getDiffDays(time, date.getTime());
        }
        return 0;
    }


    public static long getDiffDays(Date... times) {
        if (times.length > 1) {
            Date greater = times[0];
            Date smaller = times[1];
            if (greater != null && smaller != null) {
                return (greater.getTime() - smaller.getTime()) / (1000 * 3600 * 24);
            }
        }
        return 0L;
    }

    public static long getDiffDays(long... times) {
        if (times.length > 1) {
            long greater = times[0];
            long smaller = times[1];
            return (greater - smaller) / (1000 * 3600 * 24);
        }
        return 0L;
    }
}
