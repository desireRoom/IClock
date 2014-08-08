
package com.room.desire.iclock.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.room.desire.iclock.IClockApp;

public class ICSH {

    private static final String MAIN_PREF_FILE = "im";

    private static final String MAIN_TAB_INDEX = "mti";

    private static SharedPreferences getSharePreference() {
        Context context = IClockApp.getInstance();
        return context.getSharedPreferences(MAIN_PREF_FILE, Context.MODE_PRIVATE);
    }

    public static int getMainTabIndex() {
        SharedPreferences sp = getSharePreference();
        return sp.getInt(MAIN_TAB_INDEX, 0);
    }

    public static void setMainTabIndex(int i) {
        SharedPreferences sp = getSharePreference();
        Editor editor = sp.edit();
        editor.putInt(MAIN_TAB_INDEX, i);
        editor.commit();
    }

}
