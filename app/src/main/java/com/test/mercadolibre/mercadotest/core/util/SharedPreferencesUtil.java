package com.test.mercadolibre.mercadotest.core.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static final String KEY_SHARED_PREFERENCES = "mercadotest.mercadolibre.test.com";

    public static final String KEY_AMOUNT_PREFERENCES = "mercadotest.mercadolibre.test.com.AMOUNT";
    public static final String KEY_PAYMENT_METHOD_PREFERENCES = "mercadotest.mercadolibre.test.com.PAYMENT";
    public static final String KEY_CARD_ISSUER_PREFERENCES = "mercadotest.mercadolibre.test.com.CARDISSUER";
    public static final String KEY_QUOTES_PREFERENCES = "mercadotest.mercadolibre.test.com.QUOTES";

    private SharedPreferences preferences;
    private static SharedPreferencesUtil instance = null;

    public static SharedPreferencesUtil getInstance(Context context) {
        if (instance == null)
            instance = new SharedPreferencesUtil(context);

        return instance;
    }

    private SharedPreferencesUtil(Context context) {
        this.preferences = context.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(String key, Boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getPreferencesBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setPreferences(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferencesString(String key) {
        return preferences.getString(key, "");
    }

    public void setPreferences(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public long getPreferencesLong(String key) {
        return preferences.getLong(key, 0);
    }

    public void clearSharedPreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }
}