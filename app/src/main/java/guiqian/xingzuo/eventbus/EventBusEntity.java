package guiqian.xingzuo.eventbus;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author: rtzhoooou
 * @data: Mar 27, 2015 5:52:17 PM
 * @fileName: EventBusEntity.java
 * @description:
 */
public class EventBusEntity implements Parcelable, Serializable {

	private static final long serialVersionUID = -7407647749413198364L;
	public Bundle extras = new Bundle();

	public static class Builder {

		private Bundle mExtras;

		public Builder() {
			this(null);
		}

		public Builder(Context context) {
		}

		public Builder addExtras(Bundle extras) {
			if (extras != null) {
				if (mExtras == null) {
					mExtras = new Bundle(extras);
				} else {
					mExtras.putAll(extras);
				}
			}
			return this;
		}

		public Builder setExtras(Bundle extras) {
			mExtras = extras;
			return this;
		}

		public Bundle getExtras() {
			if (mExtras == null) {
				mExtras = new Bundle();
			}
			return mExtras;
		}

		public EventBusEntity buildUnstyled() {
			EventBusEntity entity = new EventBusEntity();
			return entity;
		}

		public EventBusEntity build() {

			EventBusEntity entity = buildUnstyled();

			if (mExtras != null) {
				entity.extras.putAll(mExtras);
			}

			return entity;
		}

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeBundle(extras);
	}
}
