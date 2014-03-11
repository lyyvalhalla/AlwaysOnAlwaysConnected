// com.yiyangliang.firstproject.adapter.FullScreenImageAdapter.instantiateItem(FullScreenImageAdapter.java:81)


package com.yiyangliang.firstproject.adapter;

import com.yiyangliang.firstproject.FullScreenViewActivity;
import com.yiyangliang.firstproject.MediaStoreGallery;
import com.yiyangliang.firstproject.R;
import com.yiyangliang.firstproject.StartActivity;
import com.yiyangliang.firstproject.TouchEdit;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class FullScreenImageAdapter extends PagerAdapter implements OnTouchListener{

	private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    
    ImageView imgDisplay;
    Canvas canvas;
    Paint paint;
    Matrix matrix;
    Bitmap bitmap;
    Bitmap atleredBitmap;
    
    
	public FullScreenImageAdapter(Activity activity, ArrayList<String> imagePaths) {
		this._activity = activity;
        this._imagePaths = imagePaths;
	}
	

	@Override
	public int getCount() {
		return this._imagePaths.size();
	}


	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
    public Object instantiateItem(ViewGroup container, int position) {

        Button btnClose;
        Button btnEdit;

        
  
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container, false);
  
        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        imgDisplay.setOnTouchListener((OnTouchListener) this);
        
        
         
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        imgDisplay.setImageBitmap(mutableBitmap);
               
        
        // click EDIT to load sound files
        btnEdit = (Button) viewLayout.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("TouchEdit", "Buttom Clicked");

// questions here:  how to pass data to another activity? 
				Intent i = new Intent(_activity, TouchEdit.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.putExtra("images_passed", bitmap);
				
				_activity.startActivity(i);
//				Log.v("bitmap.getWidth()", "the ");
			}
		});
        
        
       
        
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });
  
        
        
        ((ViewPager) container).addView(viewLayout); 
        return viewLayout;
	}
     
	
	 @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        ((ViewPager) container).removeView((RelativeLayout) object);
	  
	    }


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
