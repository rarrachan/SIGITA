package com.syafira.SIGITA;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by syafira rarra on 05/15/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String alarm_nama = intent.getStringExtra("alarm_nama");
        String alarm_vaksin = intent.getStringExtra("alarm_vaksin");
        String alarm_bulan = intent.getStringExtra("alarm_bulan");
        int IDs = intent.getIntExtra("IDs", 0);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent notificationIntent = new Intent(context, Splash.class);
        notificationIntent.putExtra("IDs", IDs);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, IDs, notificationIntent, 0);

        PendingIntent cancelIntent = PendingIntent.getActivity(context, IDs, new Intent(context, AlarmImunisasi.class), 0);
        cancelIntent.cancel();

        Notification.Builder nb = new Notification.Builder(context)
                .setContentTitle(alarm_nama)
                .setContentText("Imunisasi \n" + alarm_vaksin + " Pada " + alarm_bulan)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_notification))
                .setSmallIcon(R.drawable.icon_smallnotification)
                .setContentIntent(pendingIntent)
                .setDeleteIntent(cancelIntent)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setVibrate(new long[]{500, 500})
                .setTicker("SIGITA Alarm Imunisasi");
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(IDs, nb.getNotification());
    }
}

