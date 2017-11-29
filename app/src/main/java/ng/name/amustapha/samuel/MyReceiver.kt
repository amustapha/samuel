package ng.name.amustapha.samuel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent
import android.support.v4.app.NotificationCompat
import ng.name.amustapha.samuel.databases.Schedule
import java.util.*
import android.app.NotificationManager
import android.support.v4.app.TaskStackBuilder
import android.util.Log
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Started", Toast.LENGTH_LONG).show()
        Log.e("TAG", "Started")
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000,
                pendingIntent)
        val cal = Calendar.getInstance()
        val schedules = Schedule.find(Schedule::class.java, " (DOW = ? OR DAY = ? ) AND START_HOUR =? AND START_MINUTE = ? ", cal.get(Calendar.DAY_OF_WEEK).toString(), cal.get(Calendar.DAY_OF_MONTH).toString(), cal.get(Calendar.HOUR_OF_DAY).toString(), cal.get(Calendar.MINUTE).toString())
        if (schedules.size > 0){
            val mBuilder = NotificationCompat.Builder(context)
            mBuilder.setSmallIcon(R.drawable.graduate);
            mBuilder.setContentTitle("SAPA Notification!");
            mBuilder.setContentText("It's time for ${schedules[0].title}. The event is scheduled for today at ${schedules[0].location}");

            val resultIntent = Intent(context, MainActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(MainActivity::class.java)

// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent)
            val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            mBuilder.setContentIntent(resultPendingIntent)
            val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

// notificationID allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build())
            Toast.makeText(context, "notify", Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(context, "notify", Toast.LENGTH_LONG).show();
            }
    }
}
