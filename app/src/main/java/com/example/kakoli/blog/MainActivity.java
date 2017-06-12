package com.example.kakoli.blog;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button showNotificationBut, stopNotificationBut, alertButton;

    NotificationManager notificationManager;

    boolean isNotifiActive = false;

    int notifID = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNotificationBut = (Button)findViewById(R.id.showNotificationBut);
        stopNotificationBut = (Button)findViewById(R.id.stopNotificationBut);
        alertButton = (Button)findViewById(R.id.alertButton);

    }

    public void showNotification(View view) {

        NotificationCompat.Builder notificBuilder = (NotificationCompat.Builder) new
                NotificationCompat.Builder(this)
                .setContentTitle("Message")
                .setContentText("New Message")
                .setTicker("Alert New Message")
                .setSmallIcon(R.drawable.notification);

        Intent moreInfoIntent = new Intent(this, MoreInfoNotification.class);

        TaskStackBuilder tStackBuilder = TaskStackBuilder.create(this);

        tStackBuilder.addParentStack(MoreInfoNotification.class);

        tStackBuilder.addNextIntent(moreInfoIntent);

        PendingIntent pendingIntent = tStackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        notificBuilder.setContentIntent(pendingIntent);

        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notifID, notificBuilder.build());

        isNotifiActive = true;



    }

    public void stopNotification(View view) {

        if(isNotifiActive){

            notificationManager.cancel(notifID);
        }
    }

    public void setAlarm(View view) {

        Long alertTime = new GregorianCalendar().getTimeInMillis()+5*1000;

        Intent alertIntent = new Intent((this),AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                PendingIntent.getBroadcast(this, 1 ,alertIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT));



    }


}
