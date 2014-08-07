/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.glassviewer;

import java.io.IOException;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class MainActivity extends Activity {

	private int mImageCounter = 0;
	private Drawable[] mArrBitmap;
    /** Listener for tap and swipe gestures */
    private final GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {
	        switch (gesture) {
	            case SWIPE_LEFT:
	            	
	            	if(mArrBitmap != null) {
				        
	            		mImageCounter--;
		            	if(mImageCounter < 0)
		            		mImageCounter = mArrBitmap.length - 1;
		            	
		            	updateDisplay();
	            	}
	                return true;
	            case TAP:
	            case SWIPE_RIGHT:

	            	if(mArrBitmap != null) {
		            	
	            		mImageCounter++;
		            	if(mImageCounter >= mArrBitmap.length)
		            		mImageCounter = 0;
		            	
		            	updateDisplay();
	            	}
	                return true;
	            default:
	                return false;

	        }
        }
    };
    
    /** Detects gestures */
    private GestureDetector mGestureDetector;
    private ImageView mImageFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mImageFlipper = (ImageView) findViewById(R.id.imageViewer);
        
        try {
			mArrBitmap = new Drawable[] {
				Drawable.createFromStream(getAssets().open("stars1.png"), null),
				Drawable.createFromStream(getAssets().open("stars2.png"), null),
				Drawable.createFromStream(getAssets().open("stars3.png"), null)
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        if(mArrBitmap != null)
        	updateDisplay();

        mGestureDetector = new GestureDetector(this).setBaseListener(mBaseListener);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    /** Updates the main image */
    private void updateDisplay() {
        mImageFlipper.setImageDrawable(mArrBitmap[mImageCounter]);
    }
}
