package com.yiyangliang.firstproject;

import java.io.File;
import java.io.FileNotFoundException;

import com.yiyangliang.firstproject.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.LayoutInflater; 
import android.content.DialogInterface; 

public class MediaStoreGallery extends Activity implements OnClickListener, OnTouchListener {
	
	ImageView choosenImageView;
	Button choosePicture;
	
	SharedPreferences sp;
	EditText editText;
	
	
	Bitmap bmp;
	Bitmap alteredBitmap;
	Canvas canvas;
	Paint paint;
	Matrix matrix;
	float downx = 0;
	float downy = 0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_store_gallery);
		
		choosenImageView = (ImageView) this.findViewById(R.id.ChoosenImageView);
		choosePicture = (Button) this.findViewById(R.id.ChoosePictureButton);
		
		choosePicture.setOnClickListener(this);
		choosenImageView.setOnTouchListener(this);
		
		editText = (EditText) this.findViewById(R.id.editText1);
		sp = getSharedPreferences("audio_text", MODE_PRIVATE);
		
		String previousEntry = sp.getString("firsttext", null);
		if(previousEntry != null) {
			Toast.makeText(this, previousEntry, Toast.LENGTH_SHORT).show();
		}
	}

	
	public void onClick(View arg0) {
		Intent choosePictureIntent = new Intent(
			Intent.ACTION_PICK,
			android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(choosePictureIntent, 0);			
			
	}
	
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK) {
			Uri imageFileUri = intent.getData();
			Display currentDisplay = getWindowManager().getDefaultDisplay();
			float dw = currentDisplay.getWidth();
			float dh = currentDisplay.getHeight();
		try {
			BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
			bmpFactoryOptions.inJustDecodeBounds = true;
			bmp = BitmapFactory.decodeStream(
					getContentResolver().openInputStream(imageFileUri), null,
					bmpFactoryOptions);
		int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/dh);
		int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/dw);
		if (heightRatio > 1 && widthRatio > 1) {
			if (heightRatio > widthRatio) {
				bmpFactoryOptions.inSampleSize = heightRatio;
		}
		else {
		// Width ratio is larger, scale according to it
			bmpFactoryOptions.inSampleSize = widthRatio;
			}
		}
		bmpFactoryOptions.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeStream(
		getContentResolver().openInputStream(imageFileUri), null,
		bmpFactoryOptions);
		
		alteredBitmap = Bitmap.createBitmap(
				bmp.getWidth(),bmp.getHeight(),bmp.getConfig());
		canvas = new Canvas(alteredBitmap);
		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(5);
		matrix = new Matrix();
		canvas.drawBitmap(bmp, matrix, paint);
		
		choosenImageView.setImageBitmap(alteredBitmap);
		choosenImageView.setOnTouchListener(this);
	}
		catch (FileNotFoundException e) {
			Log.v("ERROR",e.toString());
				}
			}
		}
	
	
	
	
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		Log.v("MediaStoreGallery","onTouch " + action);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			
//			Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
//			SharedPreferences.Editor spe = sp.edit();
//			spe.putString("firsttext", editText.getText().toString());
			
			
			downx = event.getX();
			downy = event.getY();
			paint.setColor(Color.GREEN);
			canvas.drawRect(new Rect((int)downx-50,(int)downy-50,(int)downx+50,(int)downy+50), paint);
			showDialog();
//			canvas.drawLine(downx, downy, 200, 200, paint);
			choosenImageView.invalidate();
			Log.v("MediaStoreGallery","ACTION_DOWN " + downx + " " + downy);
			
			break;
		default:
			break;
		}
		return false;
	}
	
	
// popup window	
	private void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(MediaStoreGallery.this);
		final View viewDia = LayoutInflater.from(MediaStoreGallery.this).inflate(R.layout.custom_dialog, null);
		dialog.setTitle("please type");
		dialog.setView(viewDia);
		dialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText diaInput = (EditText) viewDia.findViewById(R.id.editText1);  
//                showClickMessage(diaInput.getText().toString()); 
//				Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
				SharedPreferences.Editor spe = sp.edit();
				spe.putString("firsttext", editText.getText().toString());
               	spe.commit();		
			}
		});
		dialog.create().show();  
	}
	
			
		
		
	private void showClickMessage()  
		    {  
//				Toast.makeText(this, editText.getText(), Toast.LENGTH_SHORT).show();
//				SharedPreferences.Editor spe = sp.edit();
//				spe.putString("firsttext", editText.getText().toString());
		
//				Toast.makeText(MediaStoreGallery.this, "you just typed:"+ message, Toast.LENGTH_SHORT).show();
		
//			String previousEntry = sp.getString("firsttext", null);
//			if(previousEntry != null) {
//				Toast.makeText(this, previousEntry, Toast.LENGTH_SHORT).show();
//			}
//		  } 
}
}
		
	

		
		
		
		
	
	