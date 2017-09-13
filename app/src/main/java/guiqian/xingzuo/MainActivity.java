package guiqian.xingzuo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import guiqian.xingzuo.jiemeng.JiemengActivity;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.fengshui.FengshuiActivity;
import guiqian.xingzuo.twelveStar.XzysTabFragment;
import guiqian.xingzuo.viewModel.StarView;
import guiqian.xingzuo.widget.BannerModel;
import guiqian.xingzuo.widget.CommonBannerView;

/**
 * MVP 形式   StarView  为交互View  Model
 * StarPresenter 为 数据Presenter
 *Activity 注入 Presenter中
 *
 */
public class MainActivity extends AppCompatActivity  implements StarView,View.OnClickListener{
    CommonBannerView mBannerView;

    @BindView(R.id.lv_xzys)
    LinearLayout mXzysLt;

    @BindView(R.id.jiemeng_lt)
    LinearLayout mJieMengLt;

    @BindView(R.id.fengshui_lt)
    LinearLayout mStarPairLt;

    @BindView(R.id.home_rt)
    RelativeLayout mHomeRTt;

    StarPresenterImpl starPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        starPresenter = new StarPresenterImpl(this);
        mHomeRTt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "!!!", Toast.LENGTH_SHORT).show();
                mHomeRTt.setBackgroundColor(getResources().getColor(R.color.tab_grey));
            }
        });
        mBannerView = (CommonBannerView) findViewById(R.id.home_banner);
        initView();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerView.pushImageCycle();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerView.startImageCycle();
    }

    public void submit(View view) {
    }

    @Override
    public void showProcess() {

    }


    public void initView(){
//        ViewGroup.LayoutParams bvlp = mBannerView.getLayoutParams();
//        bvlp.height = (int) ((float) getResources().getDisplayMetrics().widthPixels / (64.0 / 19.0));
////
//        mBannerView.setLayoutParams(bvlp);
        List<BannerModel> bannerList = new ArrayList<>();
//        BannerModel bannerModel = new BannerModel();
//        bannerModel.resourceId = R.mipmap.icon_gylq;

        BannerModel bannerModel2 = new BannerModel();
        bannerModel2.resourceId = R.mipmap.icon_znys;

        BannerModel bannerModel3 = new BannerModel();
        bannerModel3.resourceId = R.mipmap.icon_moon;
//        bannerList.add(bannerModel);
        bannerList.add(bannerModel2);
        bannerList.add(bannerModel3);
        mBannerView.loadData(this, bannerList);
        mXzysLt.setOnClickListener(this);
        mJieMengLt.setOnClickListener(this);
        mStarPairLt.setOnClickListener(this);
    }

    @Override
    public void showYunshiData(Destination destination) {
        Toast.makeText(MainActivity.this,destination.getResult().getSummary(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showJieMengData(Dream destination) {
        Toast.makeText(MainActivity.this,destination.getResult().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.lv_xzys){
//            Toast.makeText(MainActivity.this,"获取星座数据",Toast.LENGTH_SHORT).show();
//            starPresenter.getStraData();

            XzysTabFragment.newInstance(MainActivity.this);



        } else if (id == R.id.jiemeng_lt){
            Intent intent = new Intent(MainActivity.this, JiemengActivity.class);
            startActivity(intent);
        } else if(id == R.id.fengshui_lt){
            Intent intent = new Intent(MainActivity.this, FengshuiActivity.class);
            startActivity(intent);
        }

    }
}
