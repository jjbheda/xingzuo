package guiqian.xingzuo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import guiqian.xingzuo.eventbus.EventBus;


public class GSCommonActivity extends GSBaseActivity {

    private static FinishedCallBack mFinishedCallBack;
    private static Fragment mInstancefragment;

    public interface FinishedCallBack {
        void onActivityResult(Intent intent, int resultCode);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FrameLayout layout = new FrameLayout(this);
        layout.setId((int) (Math.random() * 1000000));
        setContentView(layout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Bundle args = getIntent().getExtras();
        try {
            String fragmentClassName = args.getString(GSBundleKey.FRAGMENT_CLASS);
            Fragment fragment;
            if (mInstancefragment == null)
                fragment = (Fragment) Class.forName(fragmentClassName).newInstance();
            else
                fragment = mInstancefragment;
            Bundle argument = args.getBundle(GSBundleKey.FRAGMENT_ARGS);
            if (argument != null) fragment.setArguments(argument);
            GSFragmentManager.addFragmentNotBackStack(getSupportFragmentManager(), fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void start(Activity from, Class<?> fragmentClass, Bundle args) {
        if (from == null || from.isFinishing()) return;
        Intent intent = new Intent(from, GSCommonActivity.class);
        intent.putExtra(GSBundleKey.FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(GSBundleKey.FRAGMENT_ARGS, args);
        from.startActivity(intent);
    }

    public static void startForResult(Activity from, int reqCode, Class<?> fragmentClass, Bundle args) {
        if (from == null || from.isFinishing()) return;
        Intent intent = new Intent(from, GSCommonActivity.class);
        intent.putExtra(GSBundleKey.FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(GSBundleKey.FRAGMENT_ARGS, args);
        from.startActivityForResult(intent, reqCode);
    }

    public static void startForResult(Fragment frag, int reqCode, Class<?> fragmentClass, Bundle args) {
        if (frag == null || frag.getActivity() == null || frag.getActivity().isFinishing()) return;
        Intent intent = new Intent(frag.getActivity(), GSCommonActivity.class);
        intent.putExtra(GSBundleKey.FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(GSBundleKey.FRAGMENT_ARGS, args);
        frag.startActivityForResult(intent, reqCode);
    }

    public static void setCallBack(FinishedCallBack callBack) {
        mFinishedCallBack = callBack;
    }

    public static void setmInstancefragment(Fragment fragment) {
        mInstancefragment = fragment;
    }

    public void onEvent(Intent i) {
        if (mFinishedCallBack != null)
            mFinishedCallBack.onActivityResult(i, Activity.RESULT_OK);
    }
}
