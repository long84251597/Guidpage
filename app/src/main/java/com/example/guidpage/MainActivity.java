package com.example.guidpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Path.FillType;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 实现类似于人人网APP的引导页
 * 
 * @author 超级qad
 * 
 */
public class MainActivity extends Activity {

	// 用来承载的图片的ImageView
	private ImageView iv;

	// 渐变的图片
	private Drawable[] drawables;

	// 动画
	private Animation[] animations;

	// 记录当前图片的index
	private int currentItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		iv = (ImageView) findViewById(R.id.iv);

		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						FullScreenActivity.class);
				startActivity(intent);
			}
		});

		drawables = new Drawable[] {
				getResources().getDrawable(R.drawable.guide_pic1),
				getResources().getDrawable(R.drawable.guide_pic2),
				getResources().getDrawable(R.drawable.guide_pic3) };

		animations = new Animation[] {
				AnimationUtils.loadAnimation(this, R.anim.guide_start),
				AnimationUtils.loadAnimation(this, R.anim.guide_ing),
				AnimationUtils.loadAnimation(this, R.anim.guide_end) };

		// 设置动画持续时间和监听
		for (int i = 0; i < animations.length; i++) {
			animations[i].setDuration(1500);
			animations[i].setAnimationListener(new MyAnimationListener(i));
		}

		iv.setImageDrawable(drawables[0]);
		iv.setAnimation(animations[0]);
	}

	private class MyAnimationListener implements AnimationListener {

		private int index;

		public MyAnimationListener(int index) {
			this.index = index;
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

		// 动画结束调用
		@Override
		public void onAnimationEnd(Animation animation) {
			if (index < (animations.length - 1)) {
				iv.startAnimation(animations[index + 1]);
			} else {
				currentItem++;
				if (currentItem > (drawables.length - 1)) {
					currentItem = 0;
				}
				iv.setImageDrawable(drawables[currentItem]);
				iv.startAnimation(animations[0]);
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

}
