package com.hfad.joke;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DelayedMessageService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";

    private Handler handler;
    public static final int NOTIFICATION_ID = 5453; // can be any number

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
            This method runs on the main thread, when the intent service is started, before
            the onHandleIntent(). Since it runs on the main thread, we can access UI in it.
            If we create a handler inside this method, the handler will run on the **main thread**,
            therefore it will be able to access UI.
         */

        handler = new Handler();
       return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            // Only allow one thread at a time execute this on this object
            try {
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showNotification(text);
        }
    }

    private void showText(String text) {
        Log.v("DelayedMessageService", "The message is: " + text);
    }

    private void showToast(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

        /*
            * context - context in which the toast should appear. We can't just use "this" as in
            activities, because they don't have access to the screen.

            * getApplicationContext() gives the context for whatever app is run in the foreground,
            so the toast will be diplayed even if the user switches to another app.

         */
    }

    private void showNotification(String text) {
        // Create a pending intent:
        // 1. Create an explicit intent
        Intent intent = new Intent(this, MainActivity.class);

        // 2. Pass the intent to the StackBuilder
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class); // this and line below make the back button
        stackBuilder.addNextIntent(intent);             // play nicely

        // 3. Make the pending intent
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true) // close when clicked
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText(text)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
