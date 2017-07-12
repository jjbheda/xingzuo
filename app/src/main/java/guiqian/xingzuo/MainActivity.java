package guiqian.xingzuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.viewModel.StarView;
import guiqian.xingzuo.widget.BannerModel;
import guiqian.xingzuo.widget.CommonBannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MVP 形式   StarView  为交互View  Model
 * StarPresenter 为 数据Presenter
 *Activity 注入 Presenter中
 *
 */
public class MainActivity extends AppCompatActivity  implements StarView{
    CommonBannerView mBannerView;

    @BindView(R.id.et_dream)
    EditText mEditTv;
    StarPresenterImpl starPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        starPresenter = new StarPresenterImpl(this);
        starPresenter.getStraData();
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

    @OnClick(R.id.btn_dream)
    public void submit(View view) {
        starPresenter.getJieMengData();
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
        BannerModel bannerModel = new BannerModel();
        bannerModel.resourceId = R.mipmap.destiny_01;

        BannerModel bannerModel2 = new BannerModel();
        bannerModel2.resourceId = R.mipmap.destiny_02;

        BannerModel bannerModel3 = new BannerModel();
        bannerModel3.resourceId = R.mipmap.destiny_03;
        bannerList.add(bannerModel);
        bannerList.add(bannerModel2);
        bannerList.add(bannerModel3);
        mBannerView.loadData(this, bannerList);
    }

    @Override
    public void showYunshiData(Destination destination) {
        Toast.makeText(MainActivity.this,destination.getResult().getSummary(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showJieMengData(Dream destination) {
        Toast.makeText(MainActivity.this,destination.getResult().toString(),Toast.LENGTH_SHORT).show();
    }
}
