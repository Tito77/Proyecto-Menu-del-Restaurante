package com.moviles.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moviles.clases.Platillo;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class PlatillosAdapter extends BaseAdapter {

	public CustomContext mContext;

	@Override
	public int getCount() {
		return mContext == null? 0 : mContext.lPlatillos.size();
	}

	@Override
	public Platillo getItem(int position) {
		return mContext.lPlatillos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View root = inflater.inflate(R.layout.fila_platillo, null);
		
		TextView viewNombre = (TextView)root.findViewById(R.id.infoNombre);
		TextView viewPrecio = (TextView)root.findViewById(R.id.infoPrecio);
		
		Platillo platilloEnFila = getItem(position);
		viewNombre.setText(platilloEnFila.get_sNombre()+":");
		viewPrecio.setText("₡"+platilloEnFila.get_sPrecio());
		
		return root;
		

	}

}
