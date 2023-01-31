package com.meafocus.memoriakids01.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.meafocus.memoriakids01.AdsController;
import com.meafocus.memoriakids01.MainActivity;
import com.meafocus.memoriakids01.R;
import com.meafocus.memoriakids01.common.Music;
import com.meafocus.memoriakids01.common.Shared;
import com.meafocus.memoriakids01.events.ui.BackGameEvent;
import com.meafocus.memoriakids01.events.ui.NextGameEvent;
import com.meafocus.memoriakids01.model.GameState;
import com.meafocus.memoriakids01.utils.Clock;
import com.meafocus.memoriakids01.utils.Clock.OnTimerCount;
import com.meafocus.memoriakids01.utils.FontLoader;
import com.meafocus.memoriakids01.utils.FontLoader.Font;

public class PopupWonView extends RelativeLayout implements AdsController, RewardedVideoAdListener {

	private final TextView mTime;
	private final TextView mScore;
	private final ImageView mStar1;
	private final ImageView mStar2;
	private final ImageView mStar3;
	private final Handler mHandler;
	private RewardedVideoAd rewardedVideoAd;
	int flagAds = 0;



	public PopupWonView(Context context) {
		this(context, null);

	}

	public PopupWonView(Context context, AttributeSet attrs) {


		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.popup_won_view, this, true);
		mTime = (TextView) findViewById(R.id.time_bar_text);
		mScore = (TextView) findViewById(R.id.score_bar_text);
		mStar1 = (ImageView) findViewById(R.id.star_1);
		mStar2 = (ImageView) findViewById(R.id.star_2);
		mStar3 = (ImageView) findViewById(R.id.star_3);
		ImageView mBackButton = (ImageView) findViewById(R.id.button_back);
		ImageView mNextButton = (ImageView) findViewById(R.id.button_next);
		FontLoader.setTypeface(context, new TextView[] { mTime, mScore }, Font.GROBOLD);
		setBackgroundResource(R.drawable.level_complete);
		mHandler = new Handler();

		mBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (rewardedVideoAd.isLoaded()) {
					rewardedVideoAd.show();
				}
				Shared.eventBus.notify(new BackGameEvent());
			}
		});



		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				flagAds = 1;
				if (rewardedVideoAd.isLoaded()) {
					rewardedVideoAd.show();
				}else {
					Shared.eventBus.notify(new NextGameEvent());
					flagAds = 0;
				}
			}
		});


		MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
			}
		});


		rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getContext());
		rewardedVideoAd.setRewardedVideoAdListener(this);
		loadRewardedVideoAd();
	}



	@SuppressLint({"SetTextI18n", "DefaultLocale"})
	public void setGameState(final GameState gameState) {
		int min = gameState.remainedSeconds / 60;
		int sec = gameState.remainedSeconds - min * 60;
		mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
		mScore.setText("" + 0);
		
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				animateScoreAndTime(gameState.remainedSeconds, gameState.achievedScore);
				animateStars(gameState.achievedStars);
			}
		}, 500);
	}

	private void animateStars(int start) {
		switch (start) {
		case 0:
			mStar1.setVisibility(View.GONE);
			mStar2.setVisibility(View.GONE);
			mStar3.setVisibility(View.GONE);
			break;
		case 1:
			mStar2.setVisibility(View.GONE);
			mStar3.setVisibility(View.GONE);
			mStar1.setAlpha(0f);
			animateStar(mStar1, 0);
			break;
		case 2:
			mStar3.setVisibility(View.GONE);
			mStar1.setVisibility(View.VISIBLE);
			mStar1.setAlpha(0f);
			animateStar(mStar1, 0);
			mStar2.setVisibility(View.VISIBLE);
			mStar2.setAlpha(0f);
			animateStar(mStar2, 600);
			break;
		case 3:
			mStar1.setVisibility(View.VISIBLE);
			mStar1.setAlpha(0f);
			animateStar(mStar1, 0);
			mStar2.setVisibility(View.VISIBLE);
			mStar2.setAlpha(0f);
			animateStar(mStar2, 600);
			mStar3.setVisibility(View.VISIBLE);
			mStar3.setAlpha(0f);
			animateStar(mStar3, 1200);
			break;
		default:
			break;
		}
	}
	
	private void animateStar(final View view, int delay) {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
		alpha.setDuration(100);
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(alpha, scaleX, scaleY);
		animatorSet.setInterpolator(new BounceInterpolator());
		animatorSet.setStartDelay(delay);
		animatorSet.setDuration(600);
		view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
		
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Music.showStar();
			}
		}, delay);
	}

	private void animateScoreAndTime(final int remainedSeconds, final int achievedScore) {
		final int totalAnimation = 1200;

		Clock.getInstance().startTimer(totalAnimation, 35, new OnTimerCount() {

			@SuppressLint({"DefaultLocale", "SetTextI18n"})
			@Override
			public void onTick(long millisUntilFinished) {
				float factor = millisUntilFinished / (totalAnimation * 1f); // 0.1
				int scoreToShow = achievedScore - (int) (achievedScore * factor);
				int timeToShow = (int) (remainedSeconds * factor);
				int min = timeToShow / 60;
				int sec = timeToShow - min * 60;
				mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
				mScore.setText("" + scoreToShow);
			}

			@SuppressLint({"DefaultLocale", "SetTextI18n"})
			@Override
			public void onFinish() {
				mTime.setText(" " + String.format("%02d", 0) + ":" + String.format("%02d", 0));
				mScore.setText("" + achievedScore);
			}
		});

	}


	@Override
	public void onRewardedVideoAdLoaded() {
//		Toast.makeText(getContext(), "Ads Carregado", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRewardedVideoAdOpened() {

	}

	@Override
	public void onRewardedVideoStarted() {

	}

	@Override
	public void onRewardedVideoAdClosed() {
		if (flagAds == 1){
			Shared.eventBus.notify(new NextGameEvent());
			flagAds = 0;
		}

	}

	@Override
	public void onRewarded(RewardItem rewardItem) {
//		Toast.makeText(getContext(), "ADS assistido...", Toast.LENGTH_LONG).show();
		if (flagAds == 1){
			Shared.eventBus.notify(new NextGameEvent());
			flagAds = 0;
		}
	}

	@Override
	public void onRewardedVideoAdLeftApplication() {
		if (flagAds == 1){
			Shared.eventBus.notify(new NextGameEvent());
			flagAds = 0;
		}
	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {
		if (flagAds == 1){
			Shared.eventBus.notify(new NextGameEvent());
			flagAds = 0;
		}
	}

	@Override
	public void onRewardedVideoCompleted() {
		if (flagAds == 1){
			Shared.eventBus.notify(new NextGameEvent());
			flagAds = 0;
		}
	}

	@Override
	public void showRewardedVideo() {
		if (rewardedVideoAd.isLoaded()) {
			rewardedVideoAd.show();
		} else {
			loadRewardedVideoAd();
		}

	}

	@Override
	public void loadRewardedVideoAd() {
		rewardedVideoAd.loadAd("ca-app-pub-6944068380993727/9395919730", // Test Ad
				new AdRequest.Builder().build());
	}
}
