package ng.name.amustapha.samuel.utils;

import java.util.ArrayList;
import java.util.List;

import ng.name.amustapha.samuel.databases.Category;
import ng.name.amustapha.samuel.databases.Schedule;

/**
 * Created by amustapha on 11/22/17.
 */

public class defaults {
    List<Category> defaultCategories = new ArrayList<>();
    private void init(){
        defaultCategories.add(new Category("Lecture", "Lectures"));
        defaultCategories.add(new Category("Reading", "Read"));
        defaultCategories.add(new Category("Prayer", "Time dedicated to serve God"));
        defaultCategories.add(new Category("Meeting", "Meeting"));
    }
    public void saveDefaults(){
        init();
        Category.saveInTx(defaultCategories);

    }
}
