package com.yiyangliang.firstproject;


import java.io.File;
import java.io.FileNotFoundException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MediaStoreCameraIntent extends Activity {
	
	final static int CAMERA_RESULT = 0;
	
	Uri imageFileUri;
	
	// User interface elements, specified in res/layout/main.xml
	ImageView returnedImageView;
	Button takePictureButton;
	Button saveDataButton;
	TextView titleTextView;
	TextView descriptionTextView;
	EditText titleEditText;
	EditText descriptionEditText;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		// Get references to UI elements
		returnedImageView = (ImageView) findViewById(R.id.ReturnedImageView);
		takePictureButton = (Button) findViewById(R.id.TakePictureButton);
		saveDataButton = (Button) findViewById(R.id.SaveDataButton);
		titleTextView = (TextView) findViewById(R.id.TitleTextView);
		descriptionTextView = (TextView) findViewById(R.id.DescriptionTextView);
		titleEditText = (EditText) findViewById(R.id.TitleEditText);
		descriptionEditText = (EditText) findViewById(R.id.DescriptionEditText);
		
		// Set all except takePictureButton to not be visible initially
		// View.GONE is invisible and doesn't take up space in the layout
		returnedImageView.setVisibility(View.GONE);
		saveDataButton.setVisibility(View.GONE);
		titleTextView.setVisibility(View.GONE);
		descriptionTextView.setVisibility(View.GONE);
		titleEditText.setVisibility(View.GONE);
		descriptionEditText.setVisibility(View.GONE);
		
		// when the Take Picture Button is clicked
		takePictureButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) 
			{
				//add a new record without the bitmap
				//returns the URI of the new record
				imageFileUri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
				
				//Start the camera app
				Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
				startActivityForResult(i, CAMERA_RESULT);
			}
		});
		
		saveDataButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				//update the MediaStore record with Title and Description
				ContentValues contentValues = new ContentValues(3);
				contentValues.put(Media.DISPLAY_NAME, titleEditText.getText().toString());
				contentValues.put(Media.DESCRIPTION, descriptionEditText.getText().toString());
				getContentResolver().update(imageFileUri, contentValues, null, null);
				
				//tell the suer
				Toast bread = Toast.makeText(MediaStoreCameraIntent.this, "RecordUpdated", Toast.LENGTH_SHORT);
				bread.show();
				
				//go back to the initial state, set Take Picture Button Visible
				//hide other UI elements
				takePictureButton.setVisibility(View.VISIBLE);
				
				returnedImageView.setVisibility(View.GONE);
				saveDataButton.setVisibility(View.GONE);
				titleTextView.setVisibility(View.GONE);
				descriptionTextView.setVisibility(View.GONE);
				titleEditText.setVisibility(View.GONE);
				descriptionEditText.setVisibility(View.GONE);
			}
		});
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(resultCode == RESULT_OK)
		{	
			// the camera app has returned
			
			// hide the take picture button
			takePictureButton.setVisibility(View.GONE);
			
			// show the other UI elements
			saveDataButton.setVisibility(View.VISIBLE);
			returnedImageView.setVisibility(View.VISIBLE);
			titleTextView.setVisibility(View.VISIBLE);
			descriptionTextView.setVisibility(View.VISIBLE);
			titleEditText.setVisibility(View.VISIBLE);
			descriptionEditText.setVisibility(View.VISIBLE);
			
			// scale the image
			Display currentDisplay = getWindowManager().getDefaultDisplay();
			int dw = currentDisplay.getWidth();
			int dh = currentDisplay.getHeight();
			
			try {
				// load up the img's dimensions not the image itself
				BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
				bmpFactoryOptions.inJustDecodeBounds = true;
				Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
				
				int heightRatio = (int)Math.floor(bmpFactoryOptions.outHeight/(float)dh);
				int widthRatio = (int)Math.floor(bmpFactoryOptions.outWidth/(float)dw);
				
				Log.v("HEIGHTRATIO", ""+heightRatio);
				Log.v("WIDTHRATIO",""+widthRatio);
				
				//if both of the ratios are great than 1,
				//one of the sides of the image is greater than the screen
				if (heightRatio > 1 && widthRatio >1) 
				{
					if (heightRatio > widthRatio)
					{
						// height ratio is larger, scale according to it
						bmpFactoryOptions.inSampleSize = heightRatio;
				}
				else {
					bmpFactoryOptions.inSampleSize = widthRatio;
				}		
			}
				
			
			// decode it for real
			bmpFactoryOptions.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
			
			// display it
			returnedImageView.setImageBitmap(bmp);
			}
			catch(FileNotFoundException e)
			{
				Log.v("ERROR", e.toString());
			}
		}
	}

			
			
		
		
	
				
			
			
			
		

	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

}