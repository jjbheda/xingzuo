package guiqian.xingzuo.twelveStar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import guiqian.xingzuo.R;
import guiqian.xingzuo.StarPresenterImpl;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.model.Result;
import guiqian.xingzuo.viewModel.StarView;

/**
 * Created by jiangjingbo on 2017/8/21.
 */

public class XzysActivity extends Activity
//        implements StarView
{

    GridView gv;

    private static String QUERY_URL = "http://www.d1xz.net/yunshi/";
    private ArrayList<String> mXingzuoNameList = new ArrayList<>();   //
    private ArrayList<String> mXingzuoAddressList = new ArrayList<>();  //第一星座网地址
    TextView today_tv, month_tv,yunshi_content;
    StarPresenterImpl starPresenter;

    String mCurrentStarAddress = "Aries";
    String mCurrentStarTime = "today";
    String yushiStr  = "";
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xzys_layout);
        mXingzuoNameList.add("白羊座");
        mXingzuoNameList.add("金牛座");
        mXingzuoNameList.add("双子座");
        mXingzuoNameList.add("巨蟹座");
        mXingzuoNameList.add("狮子座");
        mXingzuoNameList.add("处女座");
        mXingzuoNameList.add("天秤座");
        mXingzuoNameList.add("天蝎座");
        mXingzuoNameList.add("射手座");
        mXingzuoNameList.add("摩羯座");
        mXingzuoNameList.add("水瓶座");
        mXingzuoNameList.add("双鱼座");

        mXingzuoAddressList.add("Aries");
        mXingzuoAddressList.add("Taurus");
        mXingzuoAddressList.add("Gemini");
        mXingzuoAddressList.add("Cancer");

        mXingzuoAddressList.add("Leo");
        mXingzuoAddressList.add("Virgo");
        mXingzuoAddressList.add("Libra");
        mXingzuoAddressList.add("Scorpio");

        mXingzuoAddressList.add("Sagittarius");
        mXingzuoAddressList.add("Capricorn");
        mXingzuoAddressList.add("Aquarius");
        mXingzuoAddressList.add("Pisces");

        gv = (GridView) findViewById(R.id.gv_12xingzuo);

        gv.setAdapter(new XZGridViewAdaper(this));
        today_tv = (TextView) findViewById(R.id.today_yunshi);
        month_tv = (TextView) findViewById(R.id.month_yunshi);
        yunshi_content = (TextView) findViewById(R.id.yunshi_content);
        handler = new Handler();

        today_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today_tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                month_tv.setBackgroundColor(getResources().getColor(R.color.grey));
                mCurrentStarTime = "today";
                showUIOnThread(mCurrentStarTime+"/"+mCurrentStarAddress);
//                starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
            }
        });

        month_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                month_tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                today_tv.setBackgroundColor(getResources().getColor(R.color.grey));
                mCurrentStarTime = "month";
                showUIOnThread(mCurrentStarTime+"/"+mCurrentStarAddress);
            }
        });

//        starPresenter = new StarPresenterImpl(this);
//        starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
        showUIOnThread(mCurrentStarTime+"/"+mCurrentStarAddress);
    }

    private void showUIOnThread(final String queryOption){
        new Thread(new Runnable() {
            @Override
            public void run() {
                showUI(queryOption);
            }
        }).start();
    }

    public void showUI(String queryOption){
        Document doc = null;
        final Element element = null;
        String yunshiStr = null;
        try {
            doc = Jsoup.connect("http://www.d1xz.net/yunshi/"+queryOption).get();
            if (queryOption.startsWith("today")){
                yunshiStr = doc.select("div.txt").html();
            } else {
                Elements elements = doc.select("dd.fr");
                StringBuffer sb = new StringBuffer();
               for (int i =0;i<elements.size()-1;i++){
                   sb.append(elements.get(i).html());
               }

                yunshiStr = sb.toString();
            }

            yushiStr = Html.fromHtml((yunshiStr)).toString();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    handler.post(uiRunnable);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    Runnable uiRunnable = new Runnable() {
        @Override
        public void run() {
                yunshi_content.setText(yushiStr.replace("。","。\n"));
        }
    };

    public class XZGridViewAdaper extends BaseAdapter{
        private Context context;

        public XZGridViewAdaper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mXingzuoNameList.size();
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
            final TextView textView = (TextView) convertView.findViewById(R.id.cotent_tv);
            textView.setText(mXingzuoNameList.get(position));
            if (position ==0) {
                textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i =0;i<gv.getChildCount();i++){
                        View view = gv.getChildAt(i);
                        view.findViewById(R.id.cotent_tv).setBackgroundColor(context.getResources().getColor(R.color.grey));
                    }
                    textView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                    mCurrentStarAddress = mXingzuoAddressList.get(position);
                    Toast.makeText(XzysActivity.this,mXingzuoNameList.get(position),Toast.LENGTH_SHORT).show();
//                    starPresenter.getStraData(mCurrentStarTime,mCurrentStarName+"座");
                    showUIOnThread(mCurrentStarTime+"/"+mCurrentStarAddress);
                }
            });
            return convertView;
        }
    }
}
