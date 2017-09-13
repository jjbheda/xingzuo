package guiqian.xingzuo.twelveStar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import guiqian.xingzuo.MainActivity;
import guiqian.xingzuo.R;
import guiqian.xingzuo.base.GSBaseFragment;
import guiqian.xingzuo.base.GSCommonActivity;
import guiqian.xingzuo.widget.GSTabIndicator;

/**
 * Created by jiangjingbo on 2017/9/11.
 */

public class XzysTabFragment extends GSBaseFragment implements View.OnClickListener {
    ViewPager mViewPager;
    GSTabIndicator mGSScrollView;
    LinearLayout mIndicator_layout;
    TextView mXzName;
    public static void newInstance(Activity activity){
        GSCommonActivity.start(activity, XzysTabFragment.class, new Bundle());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.xzys_layout, container, false);

        mGSScrollView = (GSTabIndicator) rootView.findViewById(R.id.gs_hs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.xingzuo_vp);
        mIndicator_layout = (LinearLayout) rootView.findViewById(R.id.indicator_layout);
        mXzName = (TextView) rootView.findViewById(R.id.xzName);
        XingzuoRequestModel requestModel = new XingzuoRequestModel();
        requestModel.currentTime = "今日";
        requestModel.currentXingzuo = "白羊座";
        XingZuoPageAdapter pageAdapter = new XingZuoPageAdapter(getChildFragmentManager(),requestModel);
        mViewPager.setAdapter(pageAdapter);
        mGSScrollView.setViewPager(mViewPager);
        mGSScrollView.setCurrentItem(0);

        mXzName.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.xzName){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setIcon(R.mipmap.icon_moon);
                    builder.setTitle("选择一个城市");
                    //    指定下拉列表的显示数据
                    final String[] cities = {"广州", "上海", "北京", "香港", "澳门"};
                    //    设置一个下拉的列表选择项
                    builder.setItems(cities, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(getActivity(), "选择的城市为：" + cities[which], Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
        }
    }
}
