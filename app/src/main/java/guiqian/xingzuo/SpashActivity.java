package guiqian.xingzuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jiangjingbo on 2017/7/11.
 */

public class SpashActivity extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);





    }
}
