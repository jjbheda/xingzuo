package guiqian.xingzuo.starSelect;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import guiqian.xingzuo.R;

/**
 * Created by jiangjingbo on 2017/8/10.
 */

public class StarSelect extends Activity {
    GridView gridView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_select);
        gridView = (GridView) findViewById(R.id.gv_star);
    }
}
