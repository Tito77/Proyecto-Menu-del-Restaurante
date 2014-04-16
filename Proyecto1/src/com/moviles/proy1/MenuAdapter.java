package com.moviles.proy1;

import com.moviles.clases.Menu;
import com.moviles.clases.Platillo;
import com.moviles.proyecto1.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	
	public CustomContext mContext;

	@Override
	public int getCount() {
		return mContext == null? 0 : mContext.lMenus.size();
	}

	@Override
	public Menu getItem(int position) {
		return mContext.lMenus.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View root = inflater.inflate(R.layout.fila_menu, null);
		
		TextView viewNombre = (TextView)root.findViewById(R.id.menuNombre);
		
		Menu menuEnFila = getItem(position);
		viewNombre.setText(menuEnFila.get_sNombre());
		
		return root;
	}

}
