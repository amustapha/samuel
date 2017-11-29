package ng.name.amustapha.samuel

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import android.widget.Toast
import ng.name.amustapha.samuel.R.id.schedules
import ng.name.amustapha.samuel.databases.Schedule
import java.util.*
import android.media.RingtoneManager




/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions and extra parameters.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        check(this)
    }

    fun check(context: Context){
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
            val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            mBuilder.setSound(alarmSound)
            val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
// notificationID allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build())

            Toast.makeText(context, "notify", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context, "notify", Toast.LENGTH_LONG).show();
        }
        try{
            Thread.sleep(60 * 1000)
            check(context)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}
