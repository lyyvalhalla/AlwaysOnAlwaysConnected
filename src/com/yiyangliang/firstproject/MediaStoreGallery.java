package com.yiyangliang.firstproject;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

public class MediaStoreGallery extends Activity {
	
	public final static int DISPLAYWIDTH = 200;
	public final static int DISPLAYHEIGHT = 200;
	
	TextView titleTextView;
	ImageButton imageButtonA;
	
	Cursor cursor;
	Bitmap bmp;
	String imageFilePath;
	int fileColumn;
	int titleColumn;
	int displayColumn;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_store_gallery);
		
		titleTextView = (TextView) this.findViewById(R.id.TitleTextView);
		imageButtonA = (ImageButton) this.findViewById(R.id.imageButton1);
		
		String[] columns = { Media.DATA, Media._ID, Media.TITLE, Media.DISPLAY_NAME};
		cursor = managedQuery(Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
		
		fileColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
		displayColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
		
//		if(cursor.moveToFirst()) {
//			titleTextView.setText(cursor.getString(displayColumn));
//			
//			imageFilePath = cursor.getString(fileColumn);
//			bmp = getBitmap(imageFilePath);
//			
//			imageButtonA.setImageBitmap(bmp);		
//		}
		
		imageButtonA.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if (cursor.moveToNext())
						{
							titleTextView.setText(cursor.getString(displayColumn));
							
							imageFilePath = cursor.getString(fileColumn);
							bmp = getBitmap(imageFilePath);
							imageButtonA.setImageBitmap(bmp);
						}
					}
				}
			);
	}
				
		
	
	private Bitmap getBitmap(String imageFilePath2) {
		// TODO Auto-generated method stub
		
		BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
		bmpFactoryOptions.inJustDecodeBounds = true;
		Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
		
		int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight/ (float) DISPLAYHEIGHT);
		int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth/ (float) DISPLAYWIDTH);
		Log.v("HEIGHTRATIO", "" + heightRatio);
		Log.v("WIDTHRATIO", "" + widthRatio);
		
		if (heightRatio > 1 && widthRatio > 1) {
			if (heightRatio > widthRatio) {
		
				bmpFactoryOptions.inSampleSize = heightRatio;
			} else {
				bmpFactoryOptions.inSampleSize = widthRatio;
			}
		}
		// Decode it for real
		bmpFactoryOptions.inJustDecodeBounds = false;
		bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);
		return bmp;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media_store_gallery, menu);
		return true;
	}

}
