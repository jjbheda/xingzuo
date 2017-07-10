package guiqian.xingzuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.viewModel.StarView;
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
    }

    @OnClick(R.id.btn_dream)
    public void submit(View view) {
        starPresenter.getJieMengData();
    }

    @Override
    public void showProcess() {

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
