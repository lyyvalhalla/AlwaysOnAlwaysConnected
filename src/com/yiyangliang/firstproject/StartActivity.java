package com.yiyangliang.firstproject;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class StartActivity extends Activity {

	ImageButton myPeopleImage;
	ImageButton myLibraryImage;
	ImageButton myImportImage;
	ImageButton myCameraImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		myPeopleImage = (ImageButton) findViewById(R.id.imageButton1);
		myPeopleImage.setOnClickListener(
				new OnClickListener() {
        			@Override
					public void onClick(View arg0) {

        			}
				}
				
				);
		
		
		myLibraryImage = (ImageButton) findViewById(R.id.imageButton2);
		myLibraryImage.setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Log.v("StartActivity", "Buttom Clicked");
						
						Intent i = new Intent(StartActivity.this, MediaStoreGallery.class);
						startActivity(i);
					}
				
				}
				
				
	);
		
		myImportImage = (ImageButton) findViewById(R.id.imageButton3);
		myImportImage.setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Log.v("StartActivity", "Buttom Clicked");
						
						Intent i = new Intent(StartActivity.this, GridViewActivity.class);
						startActivity(i);
						
					}
					
				});
		
		myCameraImage = (ImageButton) findViewById(R.id.imageButton4);
		myCameraImage.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg4) {
						Log.v("StartActvity", "Buttom Clicked");
						
						Intent i = new Intent(StartActivity.this, MediaStoreCameraIntent.class);
						startActivity(i);
						
						
						
					}				
		});	
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
