package guiqian.xingzuo;

import guiqian.xingzuo.viewModel.StarView;

/**
 * Created by jiangjingbo on 2017/7/10.
 */

public class StarPresenterImpl implements StarPresenter {
    private StarView starView;

    public StarPresenterImpl(StarView starView){
        this.starView = starView;
    }


    @Override
    public void getStraData() {
        if (starView != null) {
            starView.showData();
        }




    }
}
