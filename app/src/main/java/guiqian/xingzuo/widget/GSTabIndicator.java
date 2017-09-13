package guiqian.xingzuo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

import guiqian.xingzuo.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class GSTabIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener{

	public GSTabIndicator(Context context) {
		super(context);
		
	}

 	private LinearLayout mTabLayout ;
 	private ViewPager mViewPager;
 	private ArrayList<TabSpecView> tabViewarr = new ArrayList<TabSpecView>();
 	private	ViewPager.OnPageChangeListener mPagerChangelistener;
	private Runnable mTabSelector;
	@SuppressLint("NewApi")
	public GSTabIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		setHorizontalScrollBarEnabled(false);
		mTabLayout = new LinearLayout(context);
		addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT,
				MATCH_PARENT));
	}

	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (mPagerChangelistener!=null) {
			mPagerChangelistener.onPageScrollStateChanged(arg0);
		}
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		if (mPagerChangelistener!=null) {
			mPagerChangelistener.onPageScrolled(arg0, arg1, arg2);
		}
		
	}

	@Override
	public void onPageSelected(int arg0) {
		
		setCurrentItem(arg0);
		if (mPagerChangelistener!=null) {
			mPagerChangelistener.onPageSelected(arg0);
		}
	}
	
	public void setViewPager(ViewPager viewPager) {

		if (mViewPager == viewPager) {
			return;
		}

		final PagerAdapter adapter = viewPager.getAdapter();
		if (adapter == null) {
			throw new IllegalStateException(
					"ViewPager does not have adapter instance.");
		}
		mViewPager = viewPager;
		viewPager.addOnPageChangeListener(this);
		notifyDataSetChanged();
		
	}

	private void notifyDataSetChanged() {
		mTabLayout.removeAllViews();
		PagerAdapter adapter = mViewPager.getAdapter();

		final int count = adapter.getCount();
		
		for (int i=0 ; i<count ;i++) {
			String title = (String) adapter.getPageTitle(i);
			TabSpecView tab = new TabSpecView(getContext(),i);
			
			tab.setBackgroundResource(R.drawable.tab_indicator_selector);
			tab.setGravity(Gravity.CENTER);
			tab.setTextAppearance(getContext(), R.style.text_ttd_tab);
			tab.setText(title);
			tabViewarr.add(tab);
			tab.setOnClickListener(mTabClickListener);
			mTabLayout.addView(tab, new LinearLayout.LayoutParams(0,MATCH_PARENT, 1));
		}
		
//		String maxSizeTitle = getMaxSizeTitle();
//		TabView tabView = new TabView(getContext());
//		tabView.setText(maxSizeTitle);
//		TextPaint paint = tabView.getPaint();
//		float textLength = paint.measureText(maxSizeTitle);
//		
//		boolean isWrapContent = false;
//		
//		if(textLength*count > CtripAppController.getWindowWidth()){
//			isWrapContent = true;
//		}
//		
//		for (int i = 0; i < count; i++) {
//			CharSequence title = adapter.getPageTitle(i);
//			if (title == null) {
//				title = EMPTY_TITLE;
//			}
//			int iconResId = 0;
//			if (iconAdapter != null) {
//				iconResId = iconAdapter.getIconResId(i);
//			}
//			addTab(i, title, iconResId, isWrapContent);
//		}
//		if (mSelectedTabIndex > count) {
//			mSelectedTabIndex = count - 1;
//		}
//		setCurrentItem(mSelectedTabIndex);
		requestLayout();
		
	}
	private class TabSpecView extends TextView {
		
		private int index;
		public TabSpecView(Context context, int index) {
			super(context);
			this.index = index;
		}
		
	}
	
	public void setCurrentItem (int position) {
		mViewPager.setCurrentItem(position);
		
		final int tabCount = mTabLayout.getChildCount();
		for (int i = 0; i < tabCount; i++) {
			final View child = mTabLayout.getChildAt(i);
			final boolean isSelected = (i == position);
			child.setSelected(isSelected);
			if (isSelected) {
				animateToTab(position);
			}
		}
	}

	private void animateToTab(final int position) {
		final TabSpecView tabView = (TabSpecView) mTabLayout.getChildAt(position);
		if (mTabSelector != null) {
			removeCallbacks(mTabSelector);
		}
		mTabSelector = new Runnable() {
			public void run() {
				final int scrollPos = tabView.getLeft()
						- (getWidth() - tabView.getWidth()) / 2;
				smoothScrollTo(scrollPos, 0);
				mTabSelector = null;
			}
		};
		post(mTabSelector);
		setTextStyle(tabView);
	}
	public void setOnpagerChangeListener(ViewPager.OnPageChangeListener listener) {
		mPagerChangelistener = listener;
	}
	
	private final OnClickListener mTabClickListener = new OnClickListener() {
		public void onClick(View view) {
			
			TabSpecView tabView = (TabSpecView) view;
			setTextStyle(tabView);
			final int newSelected = tabView.index;
			mViewPager.setCurrentItem(newSelected);
		}
	};
	
	 void setTextStyle(TabSpecView tabView) {
		if (tabViewarr.size()==0){
			return;
		}
		for (TabSpecView tv: tabViewarr) {
			TextPaint tp = tv.getPaint();
			tp.setFakeBoldText(false); 
		}
		
		TextPaint tp = tabView.getPaint();
		tp.setFakeBoldText(true); 
	}

}
