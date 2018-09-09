package com.example.niklasjahning.howtojava;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
    //https://www.youtube.com/watch?v=j6kQ9gikU-A
public class NotificationHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "channelName";
    private NotificationManager notificationManager;
    public NotificationHelper(Context base) {
        super(base);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void  createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorAccent);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getNotificationManager().createNotificationChannel(channel);

    }

    public NotificationManager getNotificationManager() {
        if(notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        }

        return notificationManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message, Intent next) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, next,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.sharp_explore_black_18dp)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

    }
}
