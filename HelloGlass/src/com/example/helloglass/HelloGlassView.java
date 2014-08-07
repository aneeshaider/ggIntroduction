package com.example.helloglass;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HelloGlassView extends FrameLayout {

	private static String TAG = "HelloGlass";
	
	public HelloGlassView(Context ctx) {
		this(ctx, null, 0);
	}
	
	public HelloGlassView(Context ctx, AttributeSet attrs) {
		this(ctx, attrs, 0);
	}
	
	public HelloGlassView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		Log.i(TAG, "view created");
		
		// load layout
		LayoutInflater.from(context).inflate(R.layout.card_hello, this);
	}

	public void updateMessage(String msg) {
		
		Log.i(TAG, "message updated");
		
		// get textview control
		TextView helloText = (TextView) findViewById(R.id.hello);
		
		// set value in textview control
		helloText.setText(msg);
		
	}
	
}
