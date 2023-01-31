package com.meafocus.memoriakids01.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meafocus.memoriakids01.R;
import com.meafocus.memoriakids01.utils.FontLoader;
import com.meafocus.memoriakids01.utils.FontLoader.Font;

import static com.meafocus.memoriakids01.MainActivity.OFFSOUND;
import static com.meafocus.memoriakids01.common.Music.OFF;



public class PopupSettingsView extends LinearLayout {

	private final ImageView mSoundImage;
	private final ImageView mSoundImage1;

	private final TextView mSoundText;
	private final TextView mSoundText1;

	public PopupSettingsView(Context context) {
		this(context, null);
	}

	public PopupSettingsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundResource(R.drawable.settings_popup);
		LayoutInflater.from(getContext()).inflate(R.layout.popup_settings_view, this, true);
		mSoundText = (TextView) findViewById(R.id.sound_off_text);
		mSoundText1 = (TextView) findViewById(R.id.music_off_text);

		TextView rateView = (TextView) findViewById(R.id.music_off_text);
		FontLoader.setTypeface(context, new TextView[]{mSoundText, rateView}, Font.GROBOLD);
		mSoundImage = (ImageView) findViewById(R.id.sound_image);
		mSoundImage1 = (ImageView) findViewById(R.id.music_image);

		View soundOff = findViewById(R.id.sound_off);
		soundOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OFF = !OFF;
				setMusicButton();

			}
		});
		View rate = findViewById(R.id.music_off);
		rate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				OFFSOUND = !OFFSOUND;
				setMusicButton2();
			}
		});
		setMusicButton();
		setMusicButton2();
	}

	@SuppressLint("SetTextI18n")
	private void setMusicButton() {
		if (OFF) {
			mSoundText.setText("Som desligado");
			mSoundImage.setImageResource(R.drawable.button_music_off);

		} else {
			mSoundText.setText("Som ligado");
			mSoundImage.setImageResource(R.drawable.button_music_on);
		}
	}


	@SuppressLint("SetTextI18n")
	private void setMusicButton2() {
		if (OFFSOUND) {
			mSoundText1.setText("Trilha sonora desligada");
			mSoundImage1.setImageResource(R.drawable.button_music_off);

		} else {
			mSoundText1.setText("Trilha sonora ligada");
			mSoundImage1.setImageResource(R.drawable.button_rate);
		}


	}
}
