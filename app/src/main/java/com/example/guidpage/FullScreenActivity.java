package com.example.guidpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYFullScreenAd;

public class FullScreenActivity extends Activity {

	private IFLYFullScreenAd ad;
	private boolean isBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ad);
		create();
	}

	private void create() {
		ad = IFLYFullScreenAd.createFullScreenAd(this,
				"762510DE4A3E5AE1FC4F02DAD73EE4F9");
		ad.setAdSize(IFLYAdSize.FULLSCREEN);
		ad.setParameter(AdKeys.SHOW_TIME_FULLSCREEN, "6000");
		ad.setParameter(AdKeys.DOWNLOAD_ALERT, "true");
		ad.loadAd(new IFLYAdListener() {

			@Override
			public void onAdReceive() {
				ad.showAd();
			}

			@Override
			public void onAdFailed(AdError arg0) {
				Toast.makeText(
						getApplicationContext(),
						arg0.getErrorCode() + "****"
								+ arg0.getErrorDescription(), 0).show();
				isBack = true;
			}

			@Override
			public void onAdClose() {
				isBack = true;
			}

			@Override
			public void onAdClick() {

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isBack) {
				return super.onKeyDown(keyCode, event);
			} else {
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}

}
