package guiqian.xingzuo.twelveStar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import guiqian.xingzuo.R;
import guiqian.xingzuo.StarPresenterImpl;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.model.Result;
import guiqian.xingzuo.viewModel.StarView;

/**
 * Created by jiangjingbo on 2017/8/21.
 */

public class XzysActivity extends Activity implements StarView{

    GridView gv;

    private ArrayList<String> mXingzuoList = new ArrayList();

    TextView today_tv, week_tv,yunshi_content;
    StarPresenterImpl starPresenter;

    String mCurrentStarName = "白羊";
    String mCurrentStarTime = "today";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xzys_layout);
        starPresenter = new StarPresenterImpl(this);

        mXingzuoList.add("白羊");
        mXingzuoList.add("金牛");
        mXingzuoList.add("双子");
        mXingzuoList.add("巨蟹");

        mXingzuoList.add("狮子");
        mXingzuoList.add("处女");
        mXingzuoList.add("天秤");
        mXingzuoList.add("天蝎");

        mXingzuoList.add("射手");
        mXingzuoList.add("摩羯");
        mXingzuoList.add("水瓶");
        mXingzuoList.add("双鱼");
        gv = (GridView) findViewById(R.id.gv_12xingzuo);

        gv.setAdapter(new XZGridViewAdaper(this));
        today_tv = (TextView) findViewById(R.id.today_yunshi);
        week_tv = (TextView) findViewById(R.id.month_yunshi);
        yunshi_content = (TextView) findViewById(R.id.yunshi_content);

        today_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today_tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                week_tv.setBackgroundColor(getResources().getColor(R.color.grey));
                mCurrentStarTime = "today";
                starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
            }
        });

        week_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                week_tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                today_tv.setBackgroundColor(getResources().getColor(R.color.grey));
                mCurrentStarTime = "month";
                starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
            }
        });

        starPresenter = new StarPresenterImpl(this);
        starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void showYunshiData(Destination detination) {
        Result result =  detination.getResult();
        if (result.getSummary() != null && !result.getSummary().isEmpty())
            yunshi_content.setText(result.getSummary().replace("。","。\n"));
        else
            yunshi_content.setText(result.getAll().replace("。","。\n"));
    }

    @Override
    public void showJieMengData(Dream destination) {

    }

    public class XZGridViewAdaper extends BaseAdapter{
        private Context context;

        public XZGridViewAdaper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mXingzuoList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                LayoutInflater localinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = localinflater.inflate(R.layout.xzys_layoutem_item,null);

            }
            final TextView textView = (TextView) convertView.findViewById(R.id.tv);
            textView.setText(mXingzuoList.get(position));
            if (position ==0) {
                textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i =0;i<gv.getChildCount();i++){
                        View view = gv.getChildAt(i);
                        view.findViewById(R.id.tv).setBackgroundColor(context.getResources().getColor(R.color.grey));
                    }
                    textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    mCurrentStarName = mXingzuoList.get(position);
//                    Toast.makeText(XzysActivity.this,mCurrentStarName,Toast.LENGTH_SHORT).show();
                    starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
                }
            });
            return convertView;
        }
    }
}
