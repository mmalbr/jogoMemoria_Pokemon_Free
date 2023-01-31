package com.meafocus.memoriakids01.common;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.meafocus.memoriakids01.R;

public class Music {

	public static boolean OFF = false;


	public static void playCorrent() {
		if (!OFF) {
			MediaPlayer mpCorrect = MediaPlayer.create(Shared.context, R.raw.correct_answer);
			mpCorrect.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mpCorrect) {
					mpCorrect.reset();
					mpCorrect.release();
					mpCorrect = null;
				}

			});
			mpCorrect.start();
		}
	}

	public static void showStar() {
		if (!OFF) {
			MediaPlayer mpStar = MediaPlayer.create(Shared.context, R.raw.star);
			mpStar.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mpStar) {
					mpStar.reset();
					mpStar.release();
					mpStar = null;
				}

			});
			mpStar.start();
		}
	}
}
