package com.yiyangliang.firstproject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TouchEdit extends Activity implements OnTouchListener {
	ImageView img;
	Canvas canvas;
    Paint paint;
    Matrix matrix;
    Bitmap bitmap;
    Bitmap alteredBitmap;
    ArrayList<String> _imagePaths;
    
    float downx = 0;
    float downy = 0;
    int newHeight; 
    int newWidth;
/*	public Bitmap getResizedBitmap(Bitmap bitmap, int newHeight, int newWidth) {
		int width = bitmap.getWidth();
	    int height = bitmap.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;

	 
	    Matrix matrix = new Matrix();
	    matrix.postScale(scaleWidth, scaleHeight);
	    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
			
		return resizedBitmap;		
	} */

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_edit);
		
		img = (ImageView) this.findViewById(R.id.imgDisplay);
		img.setOnTouchListener(this);

		Intent i = getIntent();
		bitmap = (Bitmap) i.getParcelableExtra("images_passed");
		
//		Uri imageFileUri = i.getData();
//		Display currentDisplay = getWindowManager().getDefaultDisplay();
//		
//		float dw = currentDisplay.getWidth();
//		float dh = currentDisplay.getHeight();
//		
//		try {
//			BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
//			bmpFactoryOptions.inJustDecodeBounds = true;
//			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
//			
			
//			int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/dh);
//			int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/dw);
//			if (heightRatio > 1 && widthRatio > 1) {
//				if (heightRatio > widthRatio) {
//					bmpFactoryOptions.inSampleSize = heightRatio;
//				}
//				else {
//					// Width ratio is larger, scale according to it
//					bmpFactoryOptions.inSampleSize = widthRatio;
//					}
//				}
//				bmpFactoryOptions.inJustDecodeBounds = false;
//				bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
//		
//		Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);			
//		alteredBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
//		canvas = new Canvas(alteredBitmap);
//		paint = new Paint();
//		paint.setColor(Color.GREEN);
//		paint.setStrokeWidth(5);
//		matrix = new Matrix();
//		canvas.drawBitmap(bitmap, matrix, paint);
		
		
//		int width = bitmap.getWidth();
//	    int height = bitmap.getHeight();
//	    float scaleWidth = ((float) newWidth) / width;
//	    float scaleHeight = ((float) newHeight) / height;
//	    matrix.postScale(scaleWidth, scaleHeight);
//	    matrix.reset();
//	    Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
		
		
//        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//        img.setImageBitmap(mutableBitmap);
		
	    
	    alteredBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
		canvas = new Canvas(alteredBitmap);
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(5);
		matrix = new Matrix();
		canvas.drawBitmap(bitmap, matrix, paint);
	    
		
		img.setImageBitmap(alteredBitmap);
		img.setOnTouchListener(this);
		
//		}
//		catch(FileNotFoundException e) {
//			Log.v("ERROR", e.toString());
//		}
 	}
	

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		Log.v("FullScreenImageAdapter","onTouch " + action);
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			paint.setColor(Color.BLUE);
			canvas.drawRect(new Rect((int)downx-50,(int)downy-50,(int)downx+50,(int)downy+50), paint);	
			img.invalidate();
			Log.v("TouchEdit","ACTION_DOWN " + downx + " " + downy);
			break;
		default:
			break;
		}
		return false;
	}

}
