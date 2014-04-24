package com.moviles.fragments;

import com.moviles.adapters.OrdenesAdapter;
import com.moviles.proy1.CustomContext;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListaOrdenesFragment extends ListFragment {
	
	private CustomContext mContext;
	
	@Override
	public void onAttach(Activity activity){
		
		super.onAttach(activity);
		mContext = (CustomContext) activity.getApplicationContext();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		OrdenesAdapter adapter = new OrdenesAdapter();
		adapter.mContext = this.mContext;
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
