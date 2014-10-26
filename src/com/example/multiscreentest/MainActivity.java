package com.example.multiscreentest;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static int ldpi = 120;
	private final static int mdpi = 160;
	private final static int tvdpi = 213;
	private final static int hdpi = 240;
	private final static int xhdpi = 320;
	private final static int xxhdpi = 480;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
        TextView info = (TextView)findViewById(R.id.screen_info);
        String screenInfo = getScreenInfo(); 
        info.setText(screenInfo);
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private String getScreenInfo() {
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int pxWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int pxHeight = metric.heightPixels;  // 屏幕高度（像素）
        String px = "px:"+pxWidth+"*"+pxHeight;

        float density = metric.density;
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）        
        String dpiCode = "else";
        switch (densityDpi) {
		case ldpi:
			dpiCode = "l 120 0.75";
			break;
		case mdpi:
			dpiCode = "m 160 1";
			break;
		case tvdpi:
			dpiCode = "tv 213 1.33 ";
			break;
		case hdpi:
			dpiCode = "h 240 1.5";
			break;
		case xhdpi:
			dpiCode = "xh 320 2";
			break;
		case xxhdpi:
			dpiCode = "xxh 480 3";
			break;
		default:
			dpiCode = "else "+densityDpi+" "+density;
			break;
		}
        dpiCode = "dpi:" + dpiCode;
        
        
        int dpWidth = (int) (pxWidth / density);
        int dpHeight = (int) (pxHeight / density); 
        String dp = "dp:"+dpWidth+"*"+dpHeight;
        
		return px + ", " + dpiCode + ", " + dp;
	}
}
    