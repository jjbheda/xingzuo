package guiqian.xingzuo.fengshui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import guiqian.xingzuo.PairPresenter;
import guiqian.xingzuo.R;

/**
 * Created by jiangjingbo on 2017/8/28.
 */

public class FengshuiActivity extends Activity  implements PairPresenter {

    ListView lv_fengshui;
    String yushiStr  = "";
    private Handler handler;
    FengshuiAdapter adapter = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fengshui_layout);
        lv_fengshui = (ListView) findViewById(R.id.lv_fengshui);
        showUIOnThread("");

        handler = new Handler();
    }

    Runnable uiRunnable = new Runnable() {
        @Override
        public void run() {
            if (adapter !=null){
                lv_fengshui.setAdapter(adapter);
            }

        }
    };

    public void showUI(String queryOption) {
        Document doc = null;
        final Element element = null;
        try {
            doc = Jsoup.connect("http://www.d1xz.net/fsml/aqfs/" ).get();
            Elements elements = doc.select("ul.words_list_ui li a.t");
            Elements elements_p =  doc.select("ul.words_list_ui li p");
                ArrayList<DYXZModel> list = new ArrayList<>();
                for (int i = 0; i < elements.size(); i++) {
                    DYXZModel model = new DYXZModel();
                    model.title = elements.get(i).html();
                    model.content = elements_p.get(i).html();
                    model.url = elements.get(i).attributes().get("href");
                    list.add(model);
                }

            adapter = new FengshuiAdapter(FengshuiActivity.this,list);

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

    private void showUIOnThread(final String queryOption){
        new Thread(new Runnable() {
            @Override
            public void run() {
                showUI(queryOption);
            }
        }).start();
    }

    @Override
    public void StarPair(String xignzuo1, String xingzuo2) {

    }
}
