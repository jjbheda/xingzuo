package guiqian.xingzuo;

import android.util.Log;
import android.widget.Toast;

import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.viewModel.StarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangjingbo on 2017/7/10.
 */

public class StarPresenterImpl implements StarPresenter {
    private StarView starView;
    private Call<Destination> callPost;
    private Call<Dream> dreamCallPost;

    public StarPresenterImpl(StarView starView){
        this.starView = starView;
    }
    @Override
    public void getStraData() {
        callPost = APIUtil.get().getRetrofitService().getDestination("efa739632f4a49bda66ed726a55490af","金牛座", "today","JSON",true);

        callPost.enqueue(new Callback<Destination>() {

            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                Destination destination =   response.body();
                if (destination.getErrorCode()==0){
                   starView.showYunshiData(destination);
                }
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.e("TAG",t.toString());
            }
        });

    }

    @Override
    public void getJieMengData() {
        dreamCallPost = APIUtil.get().getRetrofitService().getDreamParse("67a232c0764a428787986119d4dadf12","妈妈");

        dreamCallPost.enqueue(new Callback<Dream>() {

            @Override
            public void onResponse(Call<Dream> call, Response<Dream> response) {

                Dream dream =   response.body();
                if (dream.getErrorCode()==0){
                    starView.showJieMengData(dream);
                }

            }

            @Override
            public void onFailure(Call<Dream> call, Throwable t) {
                Log.e("TAG",t.toString());
            }
        });
    }
}
