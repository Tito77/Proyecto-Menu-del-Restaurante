package com.cajero.ordenes.adapters;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AdaptadorImagen extends BaseAdapter {

	int mGalleryItemBackground;
	private Context mContext;
	
	private Integer[] mImagenID={
			com.cajero.ordenes.R.drawable.imagenmenu,
			com.cajero.ordenes.R.drawable.muevacolor,
			com.cajero.ordenes.R.drawable.colores,		
	};
	
	public AdaptadorImagen(Context c) {
		// TODO Auto-generated constructor stub
		mContext=c;
		TypedArray attr= mContext.obtainStyledAttributes(com.cajero.ordenes.R.styleable.HelloGalery);
		mGalleryItemBackground=attr.getResourceId(com.cajero.ordenes.R.styleable.HelloGalery_android_galleryItemBackground, 0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImagenID.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(mContext);
		imageView.setImageResource(mImagenID[arg0]);

		//imageView.setLayoutParams(new Gallery.LayoutParams(150,100));
		//imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setBackgroundResource(mGalleryItemBackground);
		return imageView;
	}

}
