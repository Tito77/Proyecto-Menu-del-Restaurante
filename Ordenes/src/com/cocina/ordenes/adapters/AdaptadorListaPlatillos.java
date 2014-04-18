package com.cocina.ordenes.adapters;

import java.util.ArrayList;

import com.cocina.ordenes.R;
import com.cocina.ordenes.estructuras.DetalleOrden;
import com.cocina.ordenes.estructuras.Orden;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorListaPlatillos extends ArrayAdapter<DetalleOrden> {

	//aqui se realiza el motaje del adaptador con los datos,
		//solomente se le dice cuales elementos debe seleccionar
		
		Activity context;
		ArrayList<DetalleOrden> listaPlatillos;
		
		// Le pasamos al constructor el contexto y la lista de ordenes
	    public AdaptadorListaPlatillos(Fragment context,ArrayList<DetalleOrden> listaPlatillos) {
	        super(context.getActivity(), R.layout.item_platillo, listaPlatillos);
	        this.context = context.getActivity();
	        this.listaPlatillos=listaPlatillos;
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	    	
	    	// Rescatamos cada item del listview y lo inflamos con nuestro layout
	    	 LayoutInflater inflater = context.getLayoutInflater();
	 		View item = inflater.inflate(R.layout.item_platillo, null);

	 // Definimos los elementos que tiene nuestro layout
 		TextView texto_platillos = (TextView)item.findViewById(R.id.platillos);//este es campo de texto
 		TextView texto_cantidad = (TextView)item.findViewById(R.id.cantidades);//este es campo de texto
 		TextView texto_descripcion = (TextView)item.findViewById(R.id.descripciones);//este es campo de texto
 		
	    DetalleOrden elemento = listaPlatillos.get(position);
	    	    
	    texto_platillos.setText(elemento.getPlatillo());
		texto_cantidad.setText(elemento.getCantidad());
		texto_descripcion.setText(elemento.getTexto());
	    

	    return(item);
	}
	
	
	
}
