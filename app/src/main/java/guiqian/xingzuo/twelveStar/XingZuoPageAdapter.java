package guiqian.xingzuo.twelveStar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jiangjb
 * @date 2014.11.19
 * joyTypeList :poiType集合
 * engineMap ：poiType与对应的页面请求参数
 *
 */
public class XingZuoPageAdapter extends FragmentStatePagerAdapter {

	public String[] times = new String[]{"今日","明日","本周","本月"};
	List timeList = Arrays.asList(times);
	private XingzuoRequestModel mRequestModel;
	SparseArray<XzFragment> mPageReferenceMap = new SparseArray<XzFragment>();

	public XingZuoPageAdapter(FragmentManager fm, XingzuoRequestModel requestModel) {
		super(fm);
		mRequestModel = requestModel;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return timeList.get(position).toString();
	}

	@Override
	public Fragment getItem(int position) {

		XzFragment fragment = new XzFragment();
		Bundle args =new Bundle();
		args.putSerializable("TTD_REQUEST_MODEL", mRequestModel);
		fragment.setArguments(args);
		return fragment;
		
	}

	@Override
	public int getCount() {
		return timeList.size();
	}
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);  
    }
    
    public XzFragment getFragment(int position) {
        return mPageReferenceMap.get(position);
    }


}
