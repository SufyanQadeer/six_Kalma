package com.sixkalmas.kalimasofIslam.Global;

import android.app.Application;
import android.graphics.Typeface;

import com.sixkalmas.kalimasofIslam.Model.Constants;

public class GlobalClass extends Application {
    boolean chkAsianCountry;
    public Typeface faceArabic;
    public Typeface faceContent1;
    public Typeface faceContent2;
    public Typeface faceContent3;
    public Typeface faceHeading;
    public boolean isPurchase = false;
    public Typeface robotoRegular;
    public boolean showDialog = true;

    private void setTypeFace() {
        this.faceArabic = Typeface.createFromAsset(getAssets(), Constants.ARABIC_FONT);
        this.faceHeading = Typeface.createFromAsset(getAssets(), Constants.HEADING_FONT);
        this.faceContent1 = Typeface.createFromAsset(getAssets(), Constants.CONTENT_FONT_1);
        this.faceContent2 = Typeface.createFromAsset(getAssets(), Constants.CONTENT_FONT_2);
        this.faceContent3 = Typeface.createFromAsset(getAssets(), Constants.CONTENT_FONT_3);
        this.robotoRegular = Typeface.createFromAsset(getAssets(), "Roboto_Regular.ttf");
    }

    public void onCreate() {
        super.onCreate();
        setTypeFace();



    }
}
