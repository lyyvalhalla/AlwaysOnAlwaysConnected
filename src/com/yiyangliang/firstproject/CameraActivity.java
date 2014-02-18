package com.yiyangliang.firstproject;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends Activity {
	
	final static int CAMERA_RESULT = 0;
	
	ImageView imv; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);	
		startActivityForResult(i, CAMERA_RESULT);
	}
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(resultCode == RESULT_OK)
		{
			Bundle extras = intent.getExtras();
			Bitmap bmp=(Bitmap) extras.get("data");
			
			imv = (ImageView) findViewById(R.id.ReturnedImageView);
			imv.setImageBitmap(bmp);
		}
	}
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

}
