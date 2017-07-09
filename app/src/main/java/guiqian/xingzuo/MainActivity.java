package guiqian.xingzuo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Call<Destination> callPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callPost = APIUtil.get().getRetrofitService().getDestination("efa739632f4a49bda66ed726a55490af","金牛座",
                "today","JSON",true);


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
}
