package guiqian.xingzuo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class GSBaseFragment extends Fragment  {
	protected View rptView;
	protected View newView;
	public boolean isMainPage = false;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
	    super.onResume();
		if(isMainPage)
			return;
	}
	
	@Override
	public void onPause() {
	    super.onPause();
	}

}
