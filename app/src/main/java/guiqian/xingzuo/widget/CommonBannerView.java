package guiqian.xingzuo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import guiqian.xingzuo.R;
import guiqian.xingzuo.util.DeviceHelper;


public class CommonBannerView extends FrameLayout {

	public ViewPager mViewPager;
	private ImageView[] mImageViews;
	private ImageView mImageView = null;
	private Bitmap mActivePoint, mInactivePoint;
	List<BannerModel> mBannerList = new ArrayList<BannerModel>();

	private int mImageIndex = 0;

	ViewGroup mGroup;

	private static final int POINT_SIZE = DeviceHelper.getPixelFromDip(6);

    private int pos_c = 0xFFFFFFFF;
    private int nav_c = 0xFFFFFFFF;
	private Context mContext;


	public CommonBannerView(Context context) {
		super(context);

	}

	public CommonBannerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.banner_layout, this);
		
		mContext = context;
		
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
		mViewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						// 开始图片滚动
						startImageTimerTask();
						break;
					default:
						// 停止图片滚动
						stopImageTimerTask();
						break;
				}
				return false;
			}
		});
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);
		
	}
	
	public void loadData(Context context, List<BannerModel> BannerList) {
		// 清除所有子视图
		mGroup.removeAllViews();
		// 图片广告数量
		int imageCount = BannerList.size();
		mBannerList = BannerList;
		mImageViews = new ImageView[imageCount];
		
		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			mImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
			Padding padding = getPointPadding();
			mImageView.setPadding(padding.left, padding.top, padding.right, padding.bottom);
			mImageViews[i] = mImageView;
			mImageViews[i].setImageBitmap(getPointWithCache(i == 0));
			mGroup.addView(mImageViews[i]);
		}
		ImageCycleAdapter mAdvAdapter = new ImageCycleAdapter(mContext, (List<BannerModel>)BannerList);
		mViewPager.setAdapter(mAdvAdapter);
		
          
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动  
        mViewPager.setCurrentItem((mImageViews.length) * 100);
        startImageTimerTask();
        
	}
	
	/**
	 * 轮播图片状态监听器
	 * 
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask(); // 开始下次计时
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			// 设置当前显示的图片下标
			mImageIndex = index;
			// 设置图片滚动指示器背景
			mImageViews[index%mImageViews.length].setImageBitmap(getPointWithCache(true));
			for (int i = 0; i < mImageViews.length; i++) {
				if (index%mImageViews.length != i) {
					mImageViews[i].setImageBitmap(getPointWithCache(false));
				}
			}
		}

	}
	
	/**
	 * 开始轮播(手动控制自动轮播与否，便于资源控制)
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播——用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 开始图片滚动任务
	 */
	private void startImageTimerTask() {
		if(mBannerList.size()==1)
			return;
		stopImageTimerTask();
		// 图片每3秒滚动一次
		mHandler.postDelayed(mImageTimerTask, 2500);
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (mImageViews != null) {
				// 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
				if ((++mImageIndex) == mImageViews.length) {
					mImageIndex = 0;
				}
				mViewPager.setCurrentItem(mImageIndex);
			}
		}
	};

	
	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<ImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private List<BannerModel> mAdList = new ArrayList<BannerModel>();

		private Context mContext;

		public ImageCycleAdapter(Context context, List<BannerModel> adList) {
			mContext = context;
			mAdList = adList;
			mImageViewCacheList = new ArrayList<ImageView>();
		}

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = mAdList.get(position%mImageViews.length).ImgUrl;
			int resourceId = mAdList.get(position%mImageViews.length).resourceId;
			ImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				// 设置图片点击监听
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
					}
				});
			} else {
				imageView = mImageViewCacheList.remove(0);
			}
			imageView.setTag(imageUrl);
			container.addView(imageView);
//			imageView.displayLargeImageWithProgress(imageUrl);
			imageView.setImageResource(resourceId);
//			GSImageHelper.displayImage(imageUrl, imageView);
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			container.removeView(view);
			mImageViewCacheList.add(view);
		}

	}

	/**
	 * 轮播控件的监听事件
	 * 
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 加载图片资源
		 * 
		 * @param imageURL
		 * @param imageView
		 */
		public void displayImage(String imageURL, ImageView imageView);

		/**
		 * 单击图片事件
		 * 
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(int position, View imageView);
	}


	public Bitmap getPointWithCache(boolean isActive) {
		if (isActive) {
			if (mActivePoint == null || mActivePoint.isRecycled()) {
				mActivePoint = getPoint(true);
			}
			return mActivePoint;
		} else {
			if (mInactivePoint == null || mInactivePoint.isRecycled()) {
				mInactivePoint = getPoint(false);
			}
			return mInactivePoint;
		}
	}

	public Bitmap getPoint(boolean isActive) {
		Bitmap bmp = Bitmap.createBitmap(POINT_SIZE, POINT_SIZE, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		Paint pt = new Paint();
		pt.setColor(pos_c);
		pt.setAntiAlias(true);
		pt.setStyle(Paint.Style.FILL);
		int halfSize = POINT_SIZE >> 1;
		if (isActive) {
			canvas.drawCircle(halfSize, halfSize, halfSize, pt);
		} else {
			pt.setColor(nav_c);
			pt.setAlpha(125);
			canvas.drawCircle(halfSize, halfSize, halfSize, pt);
		}
		return bmp; 	
	}
	
	public void setPositivePointColor(int color){
		pos_c = color;
	}
	
	public void setNavigatePointColor(int color){
		nav_c = color;
	}

	public Padding getPointPadding() {
		return new Padding(10, 0, 10, 0);
	}


	public class Padding {
		public final int left, top, right, bottom;

		public Padding(int left, int top, int right, int bottom) {
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
		}
	}

}
