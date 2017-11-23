package ng.name.amustapha.samuel.utils;

import android.content.Context;

import java.util.Arrays;
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
}
