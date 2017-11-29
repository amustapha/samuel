package ng.name.amustapha.samuel.databases;

import com.orm.SugarRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by amustapha on 11/20/17.
 */

public class Schedule extends SugarRecord {
    public String title;
    public Date date;
    public int silent;
    public int startHour, startMinute, stopHour, stopMinute, recurring;
    public int dow, day, month;
    public String location;
    public Category category;

    public Schedule() {

    }

    public Category getCategory() {
        return category;
    }

    public Schedule setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Schedule setCategory(String category) {
        this.category = Category.find(Category.class, " NAME = ? ", category).get(0);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Schedule setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Schedule setDate(Date date) {
        this.date = date;
        return this;
    }

    public Schedule setDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("d MMMM, y");
        try{
            this.date = format.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.date);
            dow = cal.get(Calendar.DAY_OF_WEEK);
            day = cal.get(Calendar.DAY_OF_MONTH);
            month = cal.get(Calendar.MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Schedule setStartTime(String time){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        try{
            setStartHour(format.parse(time).getHours());
            setStartMinute(format.parse(time).getMinutes());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Schedule setStopTime(String time){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
        try{
            setStopHour(format.parse(time).getHours());
            setStopMinute(format.parse(time).getMinutes());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getStartHour() {
        return startHour;
    }

    public Schedule setStartHour(int startHour) {

        this.startHour = startHour;
        return this;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public Schedule setStartMinute(int startMinute) {
        this.startMinute = startMinute;
        return this;
    }

    public int getStopHour() {
        return stopHour;
    }

    public Schedule setStopHour(int stopHour) {
        this.stopHour = stopHour;
        return this;
    }

    public int getStopMinute() {
        return stopMinute;
    }

    public Schedule setStopMinute(int stopMinute) {
        this.stopMinute = stopMinute;
        return this;
    }

    public int getRecurring() {
        return recurring;
    }

    public Schedule setRecurring(int recurring) {
        this.recurring = recurring;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", silent=" + silent +
                ", startHour=" + startHour +
                ", startMinute=" + startMinute +
                ", stopHour=" + stopHour +
                ", stopMinute=" + stopMinute +
                ", recurring=" + recurring +
                ", location='" + location + '\'' +
                ", category=" + category +
                '}';
    }

    public int getSilent() {
        return silent;
    }

    public Schedule setSilent(int silent) {
        this.silent = silent;
        return this;
    }


    public String getLocation() {
        return location;
    }

    public Schedule setLocation(String action) {
        this.location = action;
        return this;
    }
}
