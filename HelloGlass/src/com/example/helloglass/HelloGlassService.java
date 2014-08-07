package com.example.helloglass;

import com.google.android.glass.timeline.DirectRenderingCallback;
import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;

public class HelloGlassService extends Service {
	
	private LiveCard mLiveCard;
	private static final String LIVE_CARD_TAG = "stopwatch";

	 @Override
	    public int onStartCommand(Intent intent, int flags, int startId) {
	        if (mLiveCard == null) {
	            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);
				
	            mLiveCard.setDirectRenderingEnabled(true).getSurfaceHolder().addCallback(new HelloGlassViewUpdater(this));

	            Intent menuIntent = new Intent(this, MenuActivity.class);
	            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));
	            mLiveCard.attach(this);
	            mLiveCard.publish(PublishMode.REVEAL);
	            
	        } else {
	            mLiveCard.navigate();
	        }

	        return START_STICKY;
	    }

	    @Override
	    public void onDestroy() {
	        if (mLiveCard != null && mLiveCard.isPublished()) {
	            mLiveCard.unpublish();
	            mLiveCard = null;
	        }
	        super.onDestroy();
	    }

	    @Override
		public IBinder onBind(Intent intent) {
			return null;
		}
}
