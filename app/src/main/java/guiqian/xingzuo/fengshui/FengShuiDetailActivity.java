package guiqian.xingzuo.fengshui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import guiqian.xingzuo.R;
import guiqian.xingzuo.util.HTMLSpirit;

/**
 * Created by jiangjingbo on 2017/9/7.
 */

public class FengShuiDetailActivity extends Activity {
    TextView tv_detail;

    private Handler handler;
    String url = "";
    StringBuffer content = new StringBuffer();

    public static void newInstance(Activity activity,String url){
        Intent intent = new Intent(activity,FengShuiDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        intent.putExtras(bundle);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fengshui_detail_layout);
        tv_detail = (TextView) findViewById(R.id.tv_fengshui_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            url = bundle.getString("url");
        }

        handler = new Handler();
        showUIOnThread(url);
    }

    Runnable uiRunnable = new Runnable() {
        @Override
        public void run() {
            tv_detail.setText(content);
        }
    };

    private void showUIOnThread(final String queryOption){
        new Thread(new Runnable() {
            @Override
            public void run() {
                showUI(queryOption);
            }
        }).start();
    }
    public void showUI(String queryOption) {
        Document doc = null;
        final Element element = null;
        try {
            doc = Jsoup.connect("http://www.d1xz.net/"+url ).get();
            Elements elements = doc.select("div.common_det_con p");

            ArrayList<DYXZModel> list = new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                if (elements.size() >= 5) {
                  if (i == 1 || i == elements.size() -1 || i == elements.size()-2) {
                  } else {
                      DYXZModel model = new DYXZModel();
                      model.content = elements.get(i).html().replaceAll("\\<.*?>",""); ;;
                      model.url = elements.get(i).attributes().get("href");
                      list.add(model);
                  }
                }
            }
            for (DYXZModel model:list){
                content.append(model.content+"\n");
            }

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
}

