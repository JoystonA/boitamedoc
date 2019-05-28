package com.example.boitamedoc_v2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class NotificationReceiver extends BroadcastReceiver {
    private TextView textViewPosologie;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Build notification based on Intent
        //String message = textViewPosologie.getText().toString();
        String message ="fdfz";
        Intent MainIntent = new Intent(context, NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,42,MainIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notif)
                .setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setBigContentTitle("Votre prise de médicament de 9 h 30")
                        .setSummaryText("Posologie"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.rgb(55,24,188))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        // Show notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(42, notification);
    }

    /*public void createNotify(int hour, int minute){

        Calendar c = Calendar.getInstance();
        Date temp = c.getTime();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.PM);
        Date pm = calendar.getTime();

        if(pm.after(temp))
        {

            Intent myIntent = new Intent(, NotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(Main.this, 0, myIntent,0);

            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);


            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }

    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void scheduleNotification(Context context, String title, String text,int hour,int minute) {
        //Intent MainIntent = new Intent(context, NotificationActivity.class);
        //PendingIntent contentIntent = PendingIntent.getActivity(context,0,MainIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        //context.startActivity(MainIntent);
        Calendar c = Calendar.getInstance();
        Date temp = c.getTime();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        //calendar.set(Calendar.AM_PM, Calendar.PM);
        Date pm = calendar.getTime();
        System.out.println(pm);
        System.out.println(temp);
        if (pm.after(temp)) {

            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("text", text);
            PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, 0);
            // Schdedule notification
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,  calendar.getTimeInMillis(), pending);
        }
    }

/*
    public void scheduleNotification(Context context, long delay, int notificationId) {//delay is after how much time(in millis) from current time you want to schedule the notification
        String message = "test";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notif)
                .setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setBigContentTitle("Votre prise de médicament de 9 h 30")
                        .setSummaryText("Posologie"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.rgb(55,24,188))
                .setAutoCancel(true)
                .setOnlyAlertOnce(true);
        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);
        Notification notification = builder.build();
        Intent notificationIntent = new Intent(context, App.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    */

    public static void cancelNotification(Context context, String title, String text) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        PendingIntent pending = PendingIntent.getBroadcast(context, 42, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Cancel notification
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pending);
    }

}
