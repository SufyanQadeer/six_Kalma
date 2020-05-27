package com.sixkalmas.kalimasofIslam.Activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.sixkalmas.kalimasofIslam.Global.GlobalClass;
import com.sixkalmas.kalimasofIslam.MyWorker;
import com.sixkalmas.kalimasofIslam.R;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SufiMainActivity extends AppCompatActivity implements View.OnClickListener, RatingDialogListener {


    public static final String PREFS_NAME = "6Kalma";
    public static Activity mActivity = null;
    String bodyText = "Download beautiful Islamic App for Android \"6 Kalma of Islam\" with Arabic Audio & English/Urdu Translation.\nhttps://play.google.com/store/apps/details?id=com.cyberdesignz.kalmaofislam";
    ImageButton btnRateUs;
    ImageButton btnMoreApps;
    ImageButton btnShare;
    GlobalClass mGlobalClass;
    SharedPreferences sharedPreferences;

    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView((int) R.layout.activity_main);
        mActivity = this;
        this.mGlobalClass = (GlobalClass) getApplication();
        initializeContent();
        this.sharedPreferences = getSharedPreferences(PREFS_NAME, 0);

    }

    private void initializeContent() {
        TextView tvHeader = (TextView) findViewById(R.id.tv_header);
        this.btnShare = (ImageButton) findViewById(R.id.btn_share);
        this.btnMoreApps = (ImageButton) findViewById(R.id.btn_more);
        this.btnRateUs = (ImageButton) findViewById(R.id.btn_about);

        Button[] btnKalmas = {
                (Button) findViewById(R.id.btn_kalma1),
                (Button) findViewById(R.id.btn_kalma2),
                (Button) findViewById(R.id.btn_kalma3),
                (Button) findViewById(R.id.btn_kalma4),
                (Button) findViewById(R.id.btn_kalma5),
                (Button) findViewById(R.id.btn_kalma6)};
        btnKalmas[0].setOnClickListener(this);
        btnKalmas[1].setOnClickListener(this);
        btnKalmas[2].setOnClickListener(this);
        btnKalmas[3].setOnClickListener(this);
        btnKalmas[4].setOnClickListener(this);
        btnKalmas[5].setOnClickListener(this);
        this.btnShare.setOnClickListener(this);
        this.btnMoreApps.setOnClickListener(this);
        this.btnRateUs.setOnClickListener(this);
        tvHeader.setTypeface(this.mGlobalClass.faceHeading);
        for (int pos = 0; pos < 6; pos++) {
            btnKalmas[pos].setTypeface(this.mGlobalClass.faceContent1);
        }
    }

    private void showInterstitialAds() {

    }

    public void onButtonClick(View view) {

        {
            Intent kalmaActivity = new Intent(this, SufiKalmaDetailsActivity.class);
            switch (Integer.valueOf(Integer.parseInt(view.getTag().toString())).intValue()) {
                case 1:

                    kalmaActivity.putExtra("INDEX", 0);
                    startActivity(kalmaActivity);
                    showInterstitialAds();
                    return;
                case 2:
                    kalmaActivity.putExtra("INDEX", 1);
                    startActivity(kalmaActivity);
                    showInterstitialAds();

                    return;
                case 3:
                    kalmaActivity.putExtra("INDEX", 2);
                    startActivity(kalmaActivity);
                    showInterstitialAds();
                    return;
                case 4:
                    kalmaActivity.putExtra("INDEX", 3);
                    startActivity(kalmaActivity);
                    showInterstitialAds();
                    return;
                case 5:
                    kalmaActivity.putExtra("INDEX", 4);
                    startActivity(kalmaActivity);
                    showInterstitialAds();
                    return;
                case 6:
                    kalmaActivity.putExtra("INDEX", 5);
                    startActivity(kalmaActivity);
                    showInterstitialAds();
                    return;
                case 7:

                    String appPackageName = getPackageName();
                    try {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                        return;
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    return;
                case 8:
                    shareMessage("6 Kalma of Islam", this.bodyText);
                    return;
                case 9:

                    try {
                        Intent intent  = new Intent(this, SufiMoreAppsActivity.class);
                        startActivity(intent);
                        return;
                    } catch (ActivityNotFoundException e) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=MobiVersal+Apps"));
                        startActivity(intent);
                    }

                    return;
                case 10:
                    return;
                case 99:
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                    return;
                default:
                    return;
            }
        }
    }


    public void shareMessage(String subject, String body) {

        String sharingText = getString(R.string.sharing_text);
        Intent share = new Intent("android.intent.action.SEND");
        share.setType("text/plain");
        share.putExtra("android.intent.extra.TEXT", sharingText + "\n\n Using: " + "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(share, "Share link!"));
    }


    public void onClick(View v) {
        onButtonClick(v);
    }


    @Override
    public void onBackPressed() {

        showDialog();
    }

    private void showDialog() {


        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentday = cal.get(Calendar.DAY_OF_MONTH);

        SharedPreferences prefs = getSharedPreferences("sufi", MODE_PRIVATE);
        int setday = prefs.getInt("day", 0); //0 is the default value.


        Log.e("sufi_currentday", currentday + "");
        Log.e("sufi_setday", setday + "");

        if (currentday > setday) {
            new AppRatingDialog.Builder()
                    .setPositiveButtonText("Submit")
                    .setNegativeButtonText("Cancel")
                    .setNeutralButtonText("Later")
                    .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                    .setDefaultRating(3)
                    .setTitle("Rate this application")
                    .setDescription("How was your experience with us?")
                    .setCommentInputEnabled(false)
                    .setCommentBackgroundColor(R.color.colorPrimaryDark)
                    .setWindowAnimation(R.style.MyDialogFadeAnimation)
                    .setCancelable(false)
                    .setCanceledOnTouchOutside(false)
                    .create(SufiMainActivity.this)
                    //  .setTargetFragment(MainActivity.this,1) // only if listener is implemented by fragment
                    .show();
        } else {

            finish();
        }

    }


    @Override
    public void onNegativeButtonClicked() {

        finish();

    }

    @Override
    public void onNeutralButtonClicked() {


        finish();


    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {


        if (i >= 3)
        {

            Date date = new Date();

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            day = day + 15;

            if (day > 29) {
                day = 12;
            }

            SharedPreferences.Editor editor = getSharedPreferences("sufi", MODE_PRIVATE).edit();
            editor.putInt("day", day);
            editor.apply();


            String appPackageName = this.getPackageName();
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appPackageName)));
                return;
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            } finally {

                finish();
            }

        } else {

            Toast.makeText(this, "Thanks for your Rating", Toast.LENGTH_SHORT).show();
            finish();

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(MyWorker.class, 3, TimeUnit.DAYS)
                .addTag("duaetawassul")
                .setConstraints(constraints)
                .build();
        WorkManager.getInstance().enqueueUniquePeriodicWork("duaetawassul", ExistingPeriodicWorkPolicy.KEEP, periodicWork);


    }
}
