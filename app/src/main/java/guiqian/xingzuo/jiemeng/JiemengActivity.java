package guiqian.xingzuo.jiemeng;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import guiqian.xingzuo.R;
import guiqian.xingzuo.StarPresenterImpl;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.model.DreamResult;
import guiqian.xingzuo.viewModel.StarView;

/**
 * Created by jiangjingbo on 2017/8/27.
 */

public class JiemengActivity extends Activity implements StarView {

    StarPresenterImpl starPresenter;
    EditText editText;
    TextView btn;
    TextView mJieMengContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiemeng_layout);
        starPresenter = new StarPresenterImpl(this);
        editText = (EditText)findViewById(R.id.jiemeng_et);
        btn = (TextView) findViewById(R.id.jiemeng_btn) ;
        mJieMengContent = (TextView) findViewById(R.id.jiemeng_content_tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starPresenter.getJieMengData(editText.getText().toString().isEmpty()?"":editText.getText().toString());
            }
        });
    }


    @Override
    public void showProcess() {

    }

    @Override
    public void showYunshiData(Destination destination) {

    }

    @Override
    public void showJieMengData(Dream destination) {
        if (destination!=null && destination.getResult()!=null){

            List<DreamResult> list = destination.getResult();
            StringBuffer stringBuffer = new StringBuffer();
            for (DreamResult dreamResult:list){
                stringBuffer.append(dreamResult.getContent());
            }
            mJieMengContent.setText(Html.fromHtml(stringBuffer.toString()));
        }

    }
}
