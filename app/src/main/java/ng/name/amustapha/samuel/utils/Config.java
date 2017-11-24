package ng.name.amustapha.samuel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

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

    public Bitmap getPicture(){
        Log.e("Image", get("picture"));
        byte[] decodedString = Base64.decode(get("picture"), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
