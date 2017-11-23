package ng.name.amustapha.samuel.databases;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

/**
 * Created by amustapha on 11/20/17.
 */

public class Category extends SugarRecord {
    String name;
    String description;

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }


    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

}

