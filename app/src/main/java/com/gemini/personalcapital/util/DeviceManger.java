package com.gemini.personalcapital.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

import static com.gemini.personalcapital.constant.Constant.HANDSET_ROWS;
import static com.gemini.personalcapital.constant.Constant.TABLET_ROWS;

public class DeviceManger {

    public static int getRowsNum(Activity activity) {
        if(isTablet(activity))
            return TABLET_ROWS;
        else
            return HANDSET_ROWS;
    }

    public static boolean isTablet(Context context) {
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        double wInches = displayMetrics.widthPixels / (double)displayMetrics.densityDpi;
        double hInches = displayMetrics.heightPixels / (double)displayMetrics.densityDpi;

        double screenDiagonal = Math.sqrt(Math.pow(wInches, 2) + Math.pow(hInches, 2));
        return (Math.ceil(screenDiagonal) >= 7.0);
    }
}
