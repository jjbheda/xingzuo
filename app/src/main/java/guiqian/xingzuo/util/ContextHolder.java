package guiqian.xingzuo.util;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

/**
 */
public final class ContextHolder {
    public static Context mContext;
    private static FragmentActivity currentActivity;

    public ContextHolder() {
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setCurrentActivity(FragmentActivity a) {
        currentActivity = a;
    }

    public static FragmentActivity getCurrentActivity() {
        return currentActivity;
    }
}