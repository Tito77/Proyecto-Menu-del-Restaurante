package com.moviles.proy1;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.moviles.proyecto1.R;

public class VentanaVerOrden extends DialogFragment {
	
	private CustomContext mContext;
	public IVerOrdenListener mListener;
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		mContext = (CustomContext) activity.getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View ventanaRoot = inflater.inflate(R.layout.ventana_ver_orden, null);
		getDialog().setTitle("Ver orden");
		ventanaRoot.findViewById(R.id.buttonOrder).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//INSERTAR CÓDIGO PARA MODIFICAR ORDEN EN WEBSERVICE
				
				/*Orden nuevo = new Orden();
				nuevo.mNombre = mEditNombre.getText().toString();
				nuevo.mApellidos = mEditApellido.getText().toString();
				nuevo.mTelefono = mEditTelefono.getText().toString();
				mContext.mContactos.add(nuevo);
				*/
				if(mListener != null)
					{
						mListener.verOrden();
					}
				
				//Cierra el dialogo
				dismiss();
			}
			
		});
		return ventanaRoot;
	}

}
