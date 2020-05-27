package com.sixkalmas.kalimasofIslam;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class MyWorker extends Worker {

    Context context;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private ArrayList<MoreAppsModel> playStoreAppsArraylist;

    String title, description, applink;

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("sixkalmasIslam");

    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            checktarelettese();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    public void checktarelettese() {


        GetCategory();

    }

    public void GetCategory() {
        playStoreAppsArraylist = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                playStoreAppsArraylist = new ArrayList<>();
                for (DataSnapshot uniqueKeySnapshot : snapshot.getChildren()) {

                    MoreAppsModel videoModel = uniqueKeySnapshot.getValue(MoreAppsModel.class);
                    playStoreAppsArraylist.add(videoModel);
                }


                int min = 0;
                int max = playStoreAppsArraylist.size();
                max -= 1;
                int random = new Random().nextInt((max - min) + 1) + min;


                title = playStoreAppsArraylist.get(random).getTitle();
                description = playStoreAppsArraylist.get(random).getDescription();
                applink = playStoreAppsArraylist.get(random).getLink();


                displayNotification();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e("getName==", "" + databaseError.getMessage());

            }
        });

    }

    private void displayNotification() {


        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("allahKenaamid", "allahKenaamapp", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }


        Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(applink));
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.notification_app);
        contentView.setTextViewText(R.id.title, title);
        contentView.setTextViewText(R.id.descriptionText, description);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "allahKenaamid")
                .setCustomContentView(contentView)
                .setSmallIcon(R.drawable.ic_ad_small)
                .setAutoCancel(true);

        builder.setContentIntent(contentIntent);
        notificationManager.notify(111, builder.build());

    }



}