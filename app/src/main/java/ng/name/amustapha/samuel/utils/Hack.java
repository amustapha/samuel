package ng.name.amustapha.samuel.utils;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by amustapha on 11/20/17.
 */

public class Hack {

    public static List<String> listFromResource(Context context, int res){
        return Arrays.asList(context.getResources().getStringArray(res));
    }

    public static int[] res(){
        return new int[]{android.R.id.text1, android.R.id.text2};
    }

    public static String time(int hour, int minute){
        String time = "";
        String postfix = "";
        if (hour == 0){
            time += 12;
            postfix = "am";
        }else if (hour <= 12){
            postfix = "am";
            time +=String.format("%02d", hour);
        }else{
            time +=  String.format("%02d", (hour % 12 == 0) ? 12 : hour %12);
            postfix = "pm";
        }

        time += ":" + String.format("%02d", minute) + postfix;
        return time;
    }

    public static long getCountDown(int hour, int minute){
        long time = ((hour * 60) + minute) * 60 * 1000;
        Calendar cal = Calendar.getInstance();
        Log.e("A", ""+cal.get(Calendar.MILLISECOND));
        long now = ((cal.get(Calendar.HOUR_OF_DAY) * 60 * 60) + (cal.get(Calendar.MINUTE) ) * 60 + cal.get(Calendar.SECOND)) * 1000;

        return (time - now > 0) ? time - now: 0;
    }

    public static String remainder(long l){
        l /= 1000;
        String time = "" +  String.format("%02d", l/60/60);
        time += ":" + String.format("%02d", (l/60) % 60);
        time += ":" + String.format("%02d", l%60);

        return time;
    }
}
