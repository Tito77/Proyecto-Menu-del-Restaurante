package com.cocina.ordenes.adapters;

import java.util.ArrayList;

import com.cocina.ordenes.R;
import com.cocina.ordenes.estructuras.Orden;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorListaOrdenes extends ArrayAdapter<Orden> {

	//aqui se realiza el motaje del adaptador con los datos,
	//solomente se le dice cuales elementos debe seleccionar
	
	Activity context;
	ArrayList<Orden> listaOrden;
	
	// Le pasamos al constructor el contexto y la lista de ordenes
    public AdaptadorListaOrdenes(Fragment context,ArrayList<Orden> listaOrden) {
        super(context.getActivity(), R.layout.orden_item, listaOrden);
        this.context = context.getActivity();
        this.listaOrden=listaOrden;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	// Rescatamos cada item del listview y lo inflamos con nuestro layout
    LayoutInflater inflater = context.getLayoutInflater();
    View item = inflater.inflate(R.layout.orden_item, null);

 // Definimos los elementos que tiene nuestro layout
    TextView nombre_orden = (TextView)item.findViewById(R.id.orden_item);//este es campo de texto
    TextView numero_mesa = (TextView)item.findViewById(R.id.orden_item_mesa);//este es campo de texto
    ImageView imagen= (ImageView)item.findViewById(R.id.Estado_imagen);
    
    Orden elemento = listaOrden.get(position);
   
    //nombre_orden.setText(elemento.getOrden());
    nombre_orden.setText("Orden");
    numero_mesa.setText("Mesa: #"+elemento.getmNumeroMesa());
   
    if(elemento.getmEstadoServido().equals("I")){
    	imagen.setImageResource(R.drawable.naranja);
    }
    else{
    	 if(elemento.getmEstadoServido().equals("C")){
    	    	imagen.setImageResource(R.drawable.amarillo);
    	    }
    	 else
    	 {
    		 if(elemento.getmEstadoServido().equals("S")){
     	    	imagen.setImageResource(R.drawable.verde);
     	    	}
    		 else{
    			 if(elemento.getmEstadoServido().equals("N")){
    	    	    	imagen.setImageResource(R.drawable.esmeralda);
    	    	    }
    			 else{
    				 if(elemento.getmEstadoServido().equals("P")){
     	     	    	imagen.setImageResource(R.drawable.celeste);
     	     	    	}
 	    			 else
 	    			 	{
 	    				 imagen.setImageResource(R.drawable.naranja);
 	    			 	}
    				 }
    		 }
    	 }
    }
    
    return(item);

	
	
}
}
