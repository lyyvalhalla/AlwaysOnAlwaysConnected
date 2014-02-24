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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_store_gallery);
		
		titleTextView = (TextView) this.findViewById(R.id.TitleTextView);
//		imageButtonA = (ImageButton) this.findViewById(R.id.imageButtonA);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media_store_gallery, menu);
		return true;
	}

}
