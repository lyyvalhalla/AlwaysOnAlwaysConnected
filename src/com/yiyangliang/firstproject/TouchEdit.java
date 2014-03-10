package com.yiyangliang.firstproject;

import com.yiyangliang.firstproject.adapter.FullScreenImageAdapter;
import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class TouchEdit extends Activity implements OnTouchListener {
	ImageView img;
	Canvas canvas;
    Paint paint;
    Matrix matrix;
    Bitmap bitmap;
    Bitmap alteredBitmap;
    
    float downx = 0;
    float downy = 0;
	
   
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_edit);
		
		img = (ImageView) this.findViewById(R.id.imgDisplay);
		img.setOnTouchListener((OnTouchListener) this);

		Intent i = getIntent();
		bitmap = (Bitmap) i.getParcelableExtra("images_passed");
		
		
		alteredBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
		canvas = new Canvas(alteredBitmap);
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		matrix = new Matrix();
		canvas.drawBitmap(bitmap, matrix, paint);
		
		img.setImageBitmap(alteredBitmap);
		img.setOnTouchListener(this);
 	}
	
//	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		super.onActivityResult(requestCode, resultCode, intent);
//		if (resultCode == RESULT_OK) {
//			Uri imageFileUri = intent.getData();
//			Display currentDisplay = getWindowManager().getDefaultDisplay();
//		float dw = currentDisplay.getWidth();
//		float dh = currentDisplay.getHeight();
//		
//		try {
//			BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
//			bmpFactoryOptions.inJustDecodeBounds = true;
//			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null,bmpFactoryOptions);
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
//				}
//			}
//
//			bmpFactoryOptions.inJustDecodeBounds = false;
//			bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null,bmpFactoryOptions);
//
//			alteredBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
//			canvas = new Canvas(alteredBitmap);
//			paint = new Paint();
//			paint.setColor(Color.BLUE);
//			paint.setStrokeWidth(5);
//			matrix = new Matrix();
//			canvas.drawBitmap(alteredBitmap, matrix, paint);
//
//			img.setImageBitmap(alteredBitmap);
//			img.setOnTouchListener(this);
//		}
//		catch (FileNotFoundException e) {
//			Log.v("ERROR", e.toString());
//			}
//		}
//	}
	
	

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		Log.v("FullScreenImageAdapter","onTouch " + action);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			paint.setColor(Color.GREEN);
			canvas.drawLine(downx, downy, 200, 200, paint);
			img.invalidate();
			Log.v("TouchEdit","ACTION_DOWN " + downx + " " + downy);
			break;
		default:
			break;
		}
		return false;
	}

}
