// com.yiyangliang.firstproject.adapter.FullScreenImageAdapter.instantiateItem(FullScreenImageAdapter.java:81)


package com.yiyangliang.firstproject.adapter;

import com.yiyangliang.firstproject.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class FullScreenImageAdapter extends PagerAdapter implements OnTouchListener {

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
		// TODO Auto-generated constructor stub
		this._activity = activity;
        this._imagePaths = imagePaths;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this._imagePaths.size();
	}


	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == ((RelativeLayout) object);
	}

	@Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imgDisplay;
//        Canvas canvas;
//        Paint paint;
//        Matrix matrix;
        Button btnClose;
        Button btnEdit;

        
  
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);
  
        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
        btnEdit = (Button) viewLayout.findViewById(R.id.btnEdit);
         
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 8;
        bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        imgDisplay.setImageBitmap(mutableBitmap);
        
        canvas = new Canvas(mutableBitmap);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, paint);
        
        imgDisplay.setOnTouchListener((OnTouchListener) this);
        
       
        
         
        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
	}
     
 
    float downx = 0;
    float downy = 0;
    float upx = 0;
    float upy = 0;

	
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			upx = event.getX();
			upy = event.getY();
			canvas.drawLine(downx, downy, upx, upy, paint);
			imgDisplay.invalidate();
			downx = upx;
			downy = upy;
			break;
		case MotionEvent.ACTION_UP:
			upx = event.getX();
			upy = event.getY();
			canvas.drawLine(downx, downy, upx, upy, paint);
			imgDisplay.invalidate();
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		default:
			break;
		}
		return true;
	}
	
	
	 @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	        ((ViewPager) container).removeView((RelativeLayout) object);
	  
	    }
	
}
