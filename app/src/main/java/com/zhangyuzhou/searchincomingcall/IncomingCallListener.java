package com.zhangyuzhou.searchincomingcall;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.zhangyuzhou.searchincomingcall.MainActivity.searchEngine;

/**
 * Created by zhangyuzhou on 15-8-26.
 */
public class IncomingCallListener extends BroadcastReceiver {

    public static String phoneNumber;
    public static String searchURL;
    public final static String EXTRA_MESSAGE = "com.zhangyuzhou.searchincomingcall.MESSAGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d("What I am doing now: ", action);

        //check if phone is calling
        if (action.equals("RINGING")) {
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d("Incoming call number: ", phoneNumber);
            searchURL = searchEngine + phoneNumber;
            Log.d("searchURL========", searchURL);


            Intent resultIntent = new Intent(context, SearchActivity.class);
            resultIntent.putExtra(EXTRA_MESSAGE, searchURL);

            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );

            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_action_search)
                    .setContentTitle("Search Incoming Call")
                    .setContentText("Incoming Call Number: " + phoneNumber)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setAutoCancel(true);
//                    .setFullScreenIntent(resultPendingIntent, true)
                    ;

            nBuilder.setContentIntent(resultPendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(001, nBuilder.build());
        }
    }
}
