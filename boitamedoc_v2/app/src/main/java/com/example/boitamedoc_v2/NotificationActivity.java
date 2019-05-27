package com.example.boitamedoc_v2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    public TextView textViewPosologie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textViewPosologie = findViewById(R.id.text_posologie);
    }




/*
     public void envoieSurChannel1(View v){
       String message = textViewPosologie.getText().toString();

        Intent MainIntent = new Intent(this, NotificationActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,MainIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        //broadcastIntent.putExtra("toastMessage",message);

        //PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,0);

        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_1_ID)
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
        notificationManager.notify(1,notification);
    }
*/

}
