package com.sixkalmas.kalimasofIslam.Global;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sixkalmas.kalimasofIslam.Activities.SufiMainActivity;

public class ADPreference {
    private final String CURRENTPOS = "currentpos";
    private final String INDEXTOSHOW = "indextoshow";
    private final String JSONSIZE = "jsonsize";
    private final String PKGNAME = "pkgname";
    Context context;
    SharedPreferences sharedPreferences;

    public ADPreference(Context _context) {
        this.context = _context;
        this.sharedPreferences = this.context.getSharedPreferences(SufiMainActivity.PREFS_NAME, 0);
    }

    public int getJsonSize() {
        return this.sharedPreferences.getInt("jsonsize", 0);
    }

    public void setJsonSize(int jsonSize) {
        Editor _editor = this.sharedPreferences.edit();
        _editor.putInt("jsonsize", jsonSize);
        _editor.apply();
    }

    public String getPkgName(int i) {
        return this.sharedPreferences.getString("pkgname" + i, null);
    }

    public void setPkgName(String pkgName, int i) {
        String tag = "pkgname" + i;
        Editor _editor = this.sharedPreferences.edit();
        _editor.putString(tag, pkgName);
        _editor.apply();
    }

    public int getCurrentPos() {
        return this.sharedPreferences.getInt("currentpos", 0);
    }

    public void setCurrentPos(int currentPos) {
        if (currentPos < getJsonSize()) {
            Editor _editor = this.sharedPreferences.edit();
            _editor.putInt("currentpos", currentPos);
            _editor.apply();
            return;
        }
        Editor _editor2 = this.sharedPreferences.edit();
        _editor2.putInt("currentpos", 0);
        _editor2.apply();
    }

    public int getIndexToShow() {
        return this.sharedPreferences.getInt("indextoshow", 0);
    }

    public void setIndexToShow(int currentPos) {
        if (currentPos < getJsonSize()) {
            Editor _editor = this.sharedPreferences.edit();
            _editor.putInt("indextoshow", currentPos);
            _editor.apply();
            return;
        }
        Editor _editor2 = this.sharedPreferences.edit();
        _editor2.putInt("indextoshow", 0);
        _editor2.apply();
    }
}
