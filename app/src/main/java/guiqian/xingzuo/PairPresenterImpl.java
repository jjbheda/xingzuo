package guiqian.xingzuo;

import android.util.Log;

import guiqian.xingzuo.model.Destination;
import guiqian.xingzuo.model.Dream;
import guiqian.xingzuo.model.PairResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jiangjingbo on 2017/8/28.
 */

public class PairPresenterImpl implements PairPresenter {
    private Call<PairResult> mPairCallPost;
    @Override
    public void StarPair(String xignzuo1, String xingzuo2) {
        mPairCallPost = APIUtil.get().getRetrofitService().starPair(APIService.StarPairKey,"天蝎座", "天秤座");

        mPairCallPost.enqueue(new Callback<PairResult>() {

            @Override
            public void onResponse(Call<PairResult> call, Response<PairResult> response) {

                PairResult pairResult =   response.body();
                if (pairResult.getErrorCode()==0){
//                    starView.showYunshiData(destination);
                }
            }

            @Override
            public void onFailure(Call<PairResult> call, Throwable t) {
                Log.e("TAG",t.toString());
            }
        });

    }
}
