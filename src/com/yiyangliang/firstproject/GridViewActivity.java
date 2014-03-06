package com.yiyangliang.firstproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
 
import java.util.ArrayList;

import com.yiyangliang.firstproject.adapter.GridViewImageAdapter;
import com.yiyangliang.firstproject.helper.AppConstant;
import com.yiyangliang.firstproject.helper.Utils;
 
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;




public class GridViewActivity extends Activity {
	
	private Utils utils;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewImageAdapter adapter;
    private GridView gridView;
    private int columnWidth;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		
		gridView = (GridView) findViewById(R.id.grid_view);
		utils = new Utils(this);
		InitilizeGridLayout();
		imagePaths = utils.getFilePaths();
//		Log.v("gridView:",""+imagePaths.size());
		adapter = new GridViewImageAdapter(GridViewActivity.this, imagePaths,
                columnWidth);
 
        // setting grid view adapter
        gridView.setAdapter(adapter);
	}
	
	private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());
 
        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);
 
        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid_view, menu);
		return true;
	}

}
