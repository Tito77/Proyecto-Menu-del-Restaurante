package com.moviles.proy1;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListaPlatillosFragment extends ListFragment {
	
private CustomContext mContext;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		mContext = (CustomContext) activity.getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		PlatillosAdapter adapter = new PlatillosAdapter();
		adapter.mContext = this.mContext;
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent intent = new Intent(getActivity(), InfoPlatilloActivity.class);
		intent.putExtra("posicion", position);
		startActivity(intent);
	}

}
