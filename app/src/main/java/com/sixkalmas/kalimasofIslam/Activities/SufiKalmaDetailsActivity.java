package com.sixkalmas.kalimasofIslam.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sixkalmas.kalimasofIslam.Global.GlobalClass;
import com.sixkalmas.kalimasofIslam.Model.CONST_ASAD;
import com.sixkalmas.kalimasofIslam.Model.Model;
import com.sixkalmas.kalimasofIslam.R;

public class SufiKalmaDetailsActivity extends AppCompatActivity implements OnCompletionListener, OnErrorListener, OnClickListener {


    String bodyText = "Download beautiful Islamic App for Android \"6 Kalma of Islam\" with Arabic Audio & English/Urdu Translation.\nhttps://play.google.com/store/apps/details?id=";
    ImageButton btnNextKalma;
    ImageButton btnPlay;
    ImageButton btnPrevKalma;
    ImageButton btnStop;
    ImageButton btnshare;
    ScrollView dataContainer;
    String engTrans;
    int index = 0;
    String kalma;
    String[] kalmaMeansArray = {"The word of Purity", "The word of Testimony", "The word of Glorification", "The word of Unity", "The word of Penitence", "The word of Rejecting Disbelief"};
    String kalmaName = "";
    String[] kalmaNamesArray = {"KALMA TAYYEBA", "KALMA SHAHAADAT", "KALMA TUMJEED", "KALMA TAUHID", "KALMA ASTAGHFAR", "KALMA RUD-A-KUFFER"};
    GlobalClass mGlobalClass;

    MediaPlayer f38mp;
    String[] namesArray = {"First Kalma", "Second Kalma", "Third Kalma", "Fourth Kalma", "Fifth Kalma", "Sixth Kalma"};
    LinearLayout tabLayout1;
    LinearLayout tabLayout2;
    TextView tvArabicKalma;
    TextView tvEngTrans;
    TextView tvHeader;
    TextView tvTabArabic;
    TextView tvTabMean;
    TextView tvTabTrans1;
    TextView tvTabTrans2;
    TextView tvTransliteration;
    TextView tvUrduTrans;
    String urduTrans;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_kalma);
        this.mGlobalClass = (GlobalClass) getApplicationContext();
        Intent arg = getIntent();
        if (arg != null) {
            this.index = arg.getIntExtra("INDEX", 0);
        }
        sendAnalyticsData();
        inializeContent();

    }

    private void inializeContent() {
        this.tvHeader = (TextView) findViewById(R.id.tv_header);
        this.tvTabArabic = (TextView) findViewById(R.id.tv_tab_arabic);
        this.tvTabMean = (TextView) findViewById(R.id.tv_tab_mean);
        this.tvTabTrans1 = (TextView) findViewById(R.id.tv_tab_trans_1);
        this.tvTabTrans2 = (TextView) findViewById(R.id.tv_tab_trans_2);
        this.tvArabicKalma = (TextView) findViewById(R.id.tv_kalma_arabic);
        this.tvTransliteration = (TextView) findViewById(R.id.tv_kalma_transliteration);
        this.tvEngTrans = (TextView) findViewById(R.id.tv_eng_trans);
        this.tvUrduTrans = (TextView) findViewById(R.id.tv_urdu_trans);
        this.tabLayout1 = (LinearLayout) findViewById(R.id.tab1);
        this.tabLayout2 = (LinearLayout) findViewById(R.id.tab2);
        this.dataContainer = (ScrollView) findViewById(R.id.container_layout);
        this.btnPrevKalma = (ImageButton) findViewById(R.id.btn_prev);
        this.btnNextKalma = (ImageButton) findViewById(R.id.btn_next);
        this.btnPlay = (ImageButton) findViewById(R.id.btn_play);
        this.btnStop = (ImageButton) findViewById(R.id.btn_stop);
        this.btnshare = (ImageButton) findViewById(R.id.btn_share);
        this.tvEngTrans.setVisibility(View.GONE);
        this.tvUrduTrans.setVisibility(View.GONE);
        this.btnStop.setEnabled(false);
        this.btnStop.setOnClickListener(this);
        this.btnPlay.setOnClickListener(this);
        this.btnshare.setOnClickListener(this);
        this.btnNextKalma.setOnClickListener(this);
        this.btnPrevKalma.setOnClickListener(this);
        this.tabLayout1.setOnClickListener(this);
        this.tabLayout2.setOnClickListener(this);
        this.tvHeader.setTypeface(this.mGlobalClass.faceHeading);
        this.tvTabArabic.setTypeface(this.mGlobalClass.faceContent1);
        this.tvTabMean.setTypeface(this.mGlobalClass.faceContent1);
        this.tvTabTrans1.setTypeface(this.mGlobalClass.faceContent1);
        this.tvTabTrans2.setTypeface(this.mGlobalClass.faceContent1);
        this.tvArabicKalma.setTypeface(this.mGlobalClass.faceArabic);
        this.tvUrduTrans.setTypeface(this.mGlobalClass.faceArabic);
        loadData();
    }

    public void loadData() {

        this.kalmaName = this.namesArray[this.index];
        this.tvHeader.setText(this.kalmaName);
        this.tvTabArabic.setText(this.kalmaNamesArray[this.index]);
        this.tvTabMean.setText(this.kalmaMeansArray[this.index]);

        Model model;
        model = CONST_ASAD.modelArrayList.get(index);
        tvArabicKalma.setText(model.getKalmaArabic());
        tvEngTrans.setText(model.getKalmaEnglish());
        tvUrduTrans.setText(model.getKalmaUrdu());
        tvTransliteration.setText(model.getKalmaRoman());


        if (this.index <= 0) {
            this.btnPrevKalma.setEnabled(false);
            this.btnPrevKalma.setImageResource(R.drawable.prev_h);
        } else if (!this.btnPrevKalma.isEnabled()) {
            this.btnPrevKalma.setEnabled(true);
            this.btnPrevKalma.setImageResource(R.drawable.btn_prev);
        }
        if (this.index >= 5) {
            this.btnNextKalma.setEnabled(false);
            this.btnNextKalma.setImageResource(R.drawable.next_h);
        } else if (!this.btnNextKalma.isEnabled()) {
            this.btnNextKalma.setEnabled(true);
            this.btnNextKalma.setImageResource(R.drawable.btn_next);
        }
        this.btnStop.setEnabled(false);
        this.btnPlay.setImageResource(R.drawable.btn_play);
        setScrollOnTop();
        initializeAudio();
    }

    public void initializeAudio() {
        if (this.f38mp != null) {
            this.f38mp.stop();
            this.f38mp.release();
        }
        this.f38mp = new MediaPlayer();
        int resrcKalmaAudio = getResources().getIdentifier("kalma_" + (this.index + 1), "raw", getPackageName());
        if (resrcKalmaAudio > 0) {
            this.f38mp = MediaPlayer.create(this, resrcKalmaAudio);
            this.f38mp.setOnCompletionListener(this);
            this.f38mp.setOnErrorListener(this);
        }
    }

    private final void setScrollOnTop() {
        this.dataContainer.post(new Runnable() {
            public void run() {
                if (SufiKalmaDetailsActivity.this.tvArabicKalma.getVisibility() == View.VISIBLE) {
                    SufiKalmaDetailsActivity.this.dataContainer.scrollTo(0, SufiKalmaDetailsActivity.this.tvArabicKalma.getTop());
                } else {
                    SufiKalmaDetailsActivity.this.dataContainer.scrollTo(0, SufiKalmaDetailsActivity.this.tvEngTrans.getTop());
                }
            }
        });
    }


    public void onButtonClick(View view) {
        switch (Integer.valueOf(Integer.parseInt(view.getTag().toString())).intValue()) {
            case 1:
                this.index--;
                if (this.index >= 0) {
                    loadData();
                    return;
                } else {
                    this.index = 0;
                    return;
                }
            case 2:
                this.index++;
                if (this.index <= 5) {
                    loadData();
                    return;
                } else {
                    this.index = 5;
                    return;
                }
            case 3:
                changeTab(true);
                return;
            case 4:
                changeTab(false);
                return;
            case 5:
                playAudio();
                return;
            case 6:
                reset(true);
                return;
            case 7:
                shareMessage();
                return;
            default:
                return;
        }
    }

    public void changeTab(boolean tabKalma) {
        boolean tabChanged = false;
        if (tabKalma && this.tvArabicKalma.getVisibility() == View.GONE) {
            this.tvTabArabic.setTextColor(getResources().getColor(R.color.yellow));
            this.tvTabTrans1.setTextColor(getResources().getColor(R.color.white));
            this.tvArabicKalma.setVisibility(View.VISIBLE);
            this.tvTransliteration.setVisibility(View.VISIBLE);
            this.tvEngTrans.setVisibility(View.GONE);
            this.tvUrduTrans.setVisibility(View.GONE);
            this.tabLayout1.setBackgroundResource(R.drawable.tab_color_h);
            this.tabLayout2.setBackgroundResource(R.drawable.tab_color_r);
            this.btnPlay.setVisibility(View.VISIBLE);
            this.btnStop.setVisibility(View.VISIBLE);
        } else if (!tabKalma && this.tvEngTrans.getVisibility() == View.GONE) {
            tabChanged = true;
            this.tvTabArabic.setTextColor(getResources().getColor(R.color.white));
            this.tvTabTrans1.setTextColor(getResources().getColor(R.color.yellow));
            this.tvArabicKalma.setVisibility(View.GONE);
            this.tvTransliteration.setVisibility(View.GONE);
            this.tvEngTrans.setVisibility(View.VISIBLE);
            this.tvUrduTrans.setVisibility(View.VISIBLE);
            this.tabLayout1.setBackgroundResource(R.drawable.tab_color_r);
            this.tabLayout2.setBackgroundResource(R.drawable.tab_color_h);
            this.btnPlay.setVisibility(View.GONE);
            this.btnStop.setVisibility(View.GONE);
        }
        if (tabChanged && this.f38mp != null && this.f38mp.isPlaying()) {
            this.f38mp.pause();
            this.btnPlay.setImageResource(R.drawable.btn_play);
        }
    }

    public void playAudio() {
        if (this.f38mp != null && !this.f38mp.isPlaying()) {
            this.f38mp.start();
            this.btnPlay.setImageResource(R.drawable.btn_pause);
        } else if (this.f38mp != null && this.f38mp.isPlaying()) {
            this.f38mp.pause();
            this.btnPlay.setImageResource(R.drawable.btn_play);
        }
        this.btnStop.setEnabled(true);
    }

    public void reset(boolean resetAll) {
        if (this.f38mp != null) {
            if (this.f38mp.isPlaying()) {
                this.f38mp.pause();
            }
            if (resetAll) {
                this.f38mp.seekTo(0);
                this.btnStop.setEnabled(false);
            }
        }
        this.btnPlay.setImageResource(R.drawable.btn_play);
    }

    public void shareMessage() {
        String subject=getString(R.string.app_name);
        String body;
        String str = "";
        String str2 = "";
        if (this.tvArabicKalma.getVisibility() == View.VISIBLE) {
            subject = this.kalmaNamesArray[this.index];
            body = this.kalma + "\n\n" + this.bodyText + getPackageName();
        } else {
            subject = this.kalmaNamesArray[this.index] + " Translation";
            body = this.engTrans + "\n\n" + this.urduTrans + "\n\n" + this.bodyText + getPackageName();
        }
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.setType("text/plain");
        shareIntent.putExtra("android.intent.extra.SUBJECT", subject);
        shareIntent.putExtra("android.intent.extra.TEXT", body);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }


    public void onDestroy() {
        super.onDestroy();

        if (this.f38mp != null) {
            this.f38mp.stop();
            this.f38mp.release();
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        initializeAudio();
        mp.start();
        return true;
    }

    public void onCompletion(MediaPlayer mp) {
        mp.seekTo(0);
        this.btnStop.setEnabled(false);
        this.btnPlay.setImageResource(R.drawable.btn_play);
    }

    private void sendAnalyticsData() {

    }

    public void onClick(View v) {
        onButtonClick(v);
    }
}
