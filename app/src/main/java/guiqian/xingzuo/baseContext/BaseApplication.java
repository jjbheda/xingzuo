package guiqian.xingzuo.baseContext;

import android.app.Application;

import guiqian.xingzuo.util.ContextHolder;

/**
 * Created by jiangjingbo on 2017/7/12.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.setContext(this);
    }
}
