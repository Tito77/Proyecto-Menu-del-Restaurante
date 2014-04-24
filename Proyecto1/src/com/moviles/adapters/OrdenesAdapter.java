package com.moviles.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.moviles.clases.PlatilloXOrden;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class OrdenesAdapter extends BaseAdapter {
	
	public CustomContext mContext;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContext == null? 0 : mContext._mOrden.getListaPlatillos().size();
	}

	@Override
	public PlatilloXOrden getItem(int position) {
		// TODO Auto-generated method stub
		return mContext._mOrden.getListaPlatillos().get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View root = inflater.inflate(R.layout.fila_orden, null);
		
		TextView viewNombre = (TextView)root.findViewById(R.id.tvNombre);
		TextView viewPrecio = (TextView)root.findViewById(R.id.tvPrecio);
		TextView viewCantidad = (TextView)root.findViewById(R.id.tvCantidad);
		EditText fieldNota = (EditText)root.findViewById(R.id.notasField);
		
		PlatilloXOrden platilloEnFila = getItem(position);
		viewNombre.setText(platilloEnFila.getmPlatillo().get_sNombre());
		viewPrecio.setText("₡"+platilloEnFila.getmPlatillo().get_sPrecio());
		viewCantidad.setText(platilloEnFila.getmCantidad()+"");
		fieldNota.setText(platilloEnFila.getmNotaEspecial());
		
		root.findViewById(R.id.buttonQuitar).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				quitar(position);
			}
			
		});
		return root;
	}

	protected void quitar(int position) {
		// TODO Auto-generated method stub
		this.mContext._mOrden.quitarPlatillo(position);
		notifyDataSetChanged();
	}

}
