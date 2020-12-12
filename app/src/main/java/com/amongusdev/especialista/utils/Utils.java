package com.amongusdev.especialista.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.amongusdev.especialista.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Utils {


    public static void goToNextActivityCleanStack(Activity activity, Class clase, boolean finaliza, Bundle params) {
        Intent intent = new Intent(activity, clase).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (params != null) {
            intent.putExtras(params);
        }
        activity.startActivity(intent);
        if (finaliza) {
            activity.finish();
        }
    }

    public static String timeToString(Date date) {
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm", Locale.US);
        return hour.format(date);
    }

    public static String dateToString(Calendar instance) {
        String dayW = Defines.days[instance.get(Calendar.DAY_OF_WEEK)-1];
        String month = Defines.months[instance.get(Calendar.MONTH)];
        int dayM = instance.get(Calendar.DATE);
        return dayW + "\n" + dayM +  " " + month;
    }

    public static String convert24HourToAmPm(String time) {
        if (time == null)
            return time;
        if (time.length() == 4) {
            String hour = time.substring(0, 2);
            String minutes = time.substring(2, 4);
            String meridian = "AM";

            if (hour.startsWith("00")) {
                hour = "12";
            } else if (hour.startsWith("1") || hour.startsWith("2")) {
                meridian = "PM";
                int militaryHour = Integer.parseInt(hour);
                int convertedHour;
                if (militaryHour > 12) {
                    convertedHour = (militaryHour - 12);
                    if (convertedHour < 10)
                        hour = "0" + convertedHour;
                    else
                        hour = String.valueOf(convertedHour);
                }
            }
            time = hour + ":" + minutes + " " + meridian;
        }

        return time;
    }

    public static String getValuePreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void saveValuePreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveValuePreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getValuePreferenceInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }
}
