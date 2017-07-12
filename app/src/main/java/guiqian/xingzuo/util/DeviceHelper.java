/**
 *
 */
package guiqian.xingzuo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Locale;


/**
 */
public class DeviceHelper {
    public static String TAG = DeviceHelper.class.getCanonicalName();

    private DeviceHelper() {
        throw new AssertionError();
    }

    public static int getPixelFromDip(float f) {
        return getPixelFromDip(ContextHolder.mContext.getResources().getDisplayMetrics(), f);
    }

    public static int getPixelFromDip(DisplayMetrics dm, float dip) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm) + 0.5f);
    }

    /**
     * 获取屏幕的宽高
     *
     * @param dm
     */
    public static int[] getScreenSize(DisplayMetrics dm) {
        int[] result = new int[2];
        result[0] = dm.widthPixels;
        result[1] = dm.heightPixels;
        return result;
    }

    /**
     * 获取屏幕的宽
     */
    public static int getScreenWidth() {
        return ContextHolder.mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高
     *
     */
    public static int getScreenHeight() {
        return ContextHolder.mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备 SDK版本号
     */
    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备的IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getDeviceId();
    }

    /**
     * 判断是否arm架构cpu
     *
     * @return arm返回true，否则false
     */
    public static boolean isARMCPU() {
        String cpu = Build.CPU_ABI;
        return cpu != null && cpu.toLowerCase(Locale.getDefault()).contains("arm");
    }

    /**
     * 获取设备的IMSI号
     */
    public static String getIMSI(Context context) {
        TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return teleMgr.getSubscriberId();
    }

    /**
     * 手机型号
     *
     * param context
     * return
     */
    private static String getMODEL(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String mtype = android.os.Build.MODEL; // 手机型号
        return mtype;
    }

    /**
     * 手机品牌
     *
     * param context
     * return
     */
    private static String getBRAND(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String mtyb = android.os.Build.BRAND;//手机品牌
        return mtyb;
    }

    /**
     * 手机号码
     *
     * param context
     * return
     */
    private static String getNumer(Context context) {
        TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return numer;
    }


    /**
     * 获取设备号 wifi mac + imei + cpu serial
     *
     * @return 设备号
     */
    public static String getMobileUUID(Context context) {
        String uuid = "";
        // 先获取mac
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        /* 获取mac地址 */
        try {
            if (wifiMgr != null) {
                WifiInfo info = wifiMgr.getConnectionInfo();
                if (info != null && info.getMacAddress() != null) {
                    uuid = info.getMacAddress().replace(":", "");
                }
            }
            // 再加上imei
            TelephonyManager teleMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = teleMgr.getDeviceId();
            uuid += imei;
        }catch (Exception e){
            e.printStackTrace();
        }

        // 最后再加上cpu
        String str = "", strCPU = "", cpuAddress = "";
        try {
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            ProcessBuilder cmd = new ProcessBuilder(args);
            java.lang.Process pp = cmd.start();
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    if (str.indexOf("Serial") > -1) {
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        uuid += cpuAddress;

        // 如果三个加在一起超过64位的话就截取
        if (uuid != null && uuid.length() > 64) {
            uuid = uuid.substring(0, 64);
        }
        Log.d(TAG, uuid);
        return uuid;
    }


    public static boolean isAppInstalled(Context context, String pkgName) {
        if (context == null) {
            return false;
        }

        try {
            context.getPackageManager().getPackageInfo(pkgName, PackageManager.PERMISSION_GRANTED);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static String getAppVersion(Context context) {
        String version = "";

        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
                version = info.versionName;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return version;
    }

    public static int getAppVersionCode(Context context) {
        int version = 0;

        if (context != null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo info = packageManager.getPackageInfo(context.getPackageName(), 0);
                version = info.versionCode;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return version;
    }

    public static String getNetworkIP(Context context) {

        NetworkInfo localNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if ((localNetworkInfo == null))
            return null;

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null)
            return null;

        int ipAddress = wifiInfo.getIpAddress();
        StringBuilder sb = new StringBuilder();
        sb.append(ipAddress & 0xFF).append(".");
        sb.append((ipAddress >> 8) & 0xFF).append(".");
        sb.append((ipAddress >> 16) & 0xFF).append(".");
        sb.append((ipAddress >> 24) & 0xFF);

        return sb.toString();
    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台" + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

}
