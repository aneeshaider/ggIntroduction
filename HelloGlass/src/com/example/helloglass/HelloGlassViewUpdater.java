package com.example.helloglass;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.google.android.glass.timeline.DirectRenderingCallback;

public class HelloGlassViewUpdater implements DirectRenderingCallback {

	private HelloGlassView mView;
	public HelloGlassViewUpdater(Context ctx) {
		mView = new HelloGlassView(ctx);
	}
	
	private static String TAG = "HelloGlass";
	private SurfaceHolder mHolder;
		
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		Log.i(TAG, "surface created");
		
		if(mHolder == null) {
			
			mHolder = holder;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		Log.i(TAG, "surface changed");
		
		 // Measure and layout the view with the canvas dimensions.
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        mView.measure(measuredWidth, measuredHeight);
        mView.layout(
                0, 0, mView.getMeasuredWidth(), mView.getMeasuredHeight());
        
        if(mHolder != null) {
        
        	// from now-on, can change view of the Live card at runtime
        	
        	mView.updateMessage("Hello Glass");
			Canvas canvas;
	        try {
	            canvas = holder.lockCanvas();
	        } catch (Exception e) {
	            Log.e("HelloGlassService", "Unable to lock canvas: " + e);
	            return;
	        }
	        if (canvas != null) {
	            mView.draw(canvas);
	            holder.unlockCanvasAndPost(canvas);
	        }
        }

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		Log.i(TAG, "surface destroyed");
	}

	@Override
	public void renderingPaused(SurfaceHolder arg0, boolean arg1) {

	}

}
