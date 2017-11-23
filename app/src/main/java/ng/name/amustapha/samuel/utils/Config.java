package ng.name.amustapha.samuel.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by amustapha on 11/20/17.
 */

public class Config {
    private SharedPreferences pref;

    public Config(Context c) {
        this.pref = c.getSharedPreferences("samu", Context.MODE_PRIVATE);
    }

    public void set(String key, String value){
        pref.edit().putString(key, value).apply();
    }

    public String get(String key){
        return pref.getString(key, "blank");
    }

    public boolean has(String key){
        return pref.contains(key);
    }
}
