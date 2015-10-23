package com.bsystemslimited.flexpay;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by Hecatonchries on 10/22/2015.
 */
public class SaveSharedPreference {

    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASSWORD = "password";

    static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUserName(Context context, String username) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_USER_NAME, username);
        editor.commit();
    }

    public static String getUserName(Context context) {
        return getSharedPreferences(context).getString(PREF_USER_NAME, "");
    }

    public static void setUserPassword(Context context, String password) {
        Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_USER_PASSWORD, password);
        editor.commit();
    }

    public static String getUserPassword(Context context) {
        return getSharedPreferences(context).getString(PREF_USER_PASSWORD, "");
    }


    public static void clear(Context context)
    {
        Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }

}
