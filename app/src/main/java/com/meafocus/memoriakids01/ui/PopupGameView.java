package com.meafocus.memoriakids01.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meafocus.memoriakids01.R;
import com.meafocus.memoriakids01.common.Shared;
import com.meafocus.memoriakids01.utils.FontLoader;
import com.meafocus.memoriakids01.utils.FontLoader.Font;

import static com.meafocus.memoriakids01.MainActivity.OFFSOUND;
import static com.meafocus.memoriakids01.common.Music.OFF;


public class PopupGameView extends LinearLayout {

	private final ImageView mSoundImage;
	private final ImageView mSoundImage1;

	private final TextView mSoundText;
	private final TextView mSoundText1;

	public PopupGameView(Context context) {
		this(context, null);
	}

	public PopupGameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundResource(R.drawable.games_popup);
		LayoutInflater.from(getContext()).inflate(R.layout.popup_game_view, this, true);
		mSoundText = (TextView) findViewById(R.id.game1_text);
		mSoundText1 = (TextView) findViewById(R.id.game2_text);

		TextView rateView = (TextView) findViewById(R.id.game2_text);
		FontLoader.setTypeface(context, new TextView[]{mSoundText, rateView}, Font.GROBOLD);
		mSoundImage = (ImageView) findViewById(R.id.game1_image);
		mSoundImage1 = (ImageView) findViewById(R.id.game2_image);

		View soundOff = findViewById(R.id.game1);
		soundOff.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.meafocus.coinastro")));
				} catch (android.content.ActivityNotFoundException anfe) {
					Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.meafocus.coinastro")));
				}

			}
		});
		View rate = findViewById(R.id.game2);
		rate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.meafocus.asteroides")));
				} catch (android.content.ActivityNotFoundException anfe) {
					Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.meafocus.asteroides")));
				}
			}
		});

	}





}
