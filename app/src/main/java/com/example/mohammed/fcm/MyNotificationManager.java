package com.example.mohammed.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.*;


public class MyNotificationManager {
    private Context ctx;
    public static final int NOTIFICATION_ID = 1994;

    public MyNotificationManager(Context ctx) {
        this.ctx = ctx;
    }

    // build notification
    // intent to specify where the user go when click on notification
    public void showNotification(String from , String notification , Intent intent){
        PendingIntent pendingIntent = PendingIntent.getActivity(
                ctx,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // build our notification using notification compact builder
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(ctx);
        Notification mNotification = builder.setSmallIcon(
                R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources()
                        ,R.mipmap.ic_launcher))
                .build();
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL ;

        // create notification manager to notify the user
        NotificationManager notificationManager = (NotificationManager)ctx
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID , mNotification);

        // we will call this method to build notification on MyFirebaseMessagingService class
    }
}
