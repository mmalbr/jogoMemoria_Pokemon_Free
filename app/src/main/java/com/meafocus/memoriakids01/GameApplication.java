package com.meafocus.memoriakids01;

import android.app.Application;

import com.meafocus.memoriakids01.utils.FontLoader;

public class GameApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FontLoader.loadFonts(this);

	}
}
