package guiqian.xingzuo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

public class GSBaseActivity extends FragmentActivity {

    protected String PageCode = "";
    protected String PageDescription = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            getWindow().getDecorView().clearAnimation();

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else {
                try {
                    getSupportFragmentManager().popBackStackImmediate();
                } catch (Exception e) {
                }
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
