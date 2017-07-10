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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Call<Destination> callPost;
    private Call<Dream> dreamCallPost;
    @BindView(R.id.et_dream)
    EditText mEditTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        callPost = APIUtil.get().getRetrofitService().getDestination("efa739632f4a49bda66ed726a55490af","金牛座", "today","JSON",true);

        callPost.enqueue(new Callback<Destination>() {

            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                Destination destination =   response.body();
                if (destination.getErrorCode()==0){
                    Toast.makeText(MainActivity.this,destination.getResult().getSummary(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.e("TAG",t.toString());
            }
        });

    }

    @OnClick(R.id.btn_dream)
    public void submit(View view) {
        dreamCallPost = APIUtil.get().getRetrofitService().getDreamParse("67a232c0764a428787986119d4dadf12","妈妈");

        dreamCallPost.enqueue(new Callback<Dream>() {

            @Override
            public void onResponse(Call<Dream> call, Response<Dream> response) {

                Dream dream =   response.body();
                if (dream.getErrorCode()==0){
                    Toast.makeText(MainActivity.this,dream.getResult().toString(),Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Dream> call, Throwable t) {
                Log.e("TAG",t.toString());
            }
        });
    }
}
