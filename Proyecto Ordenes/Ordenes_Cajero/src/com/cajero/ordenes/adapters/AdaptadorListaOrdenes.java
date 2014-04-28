package com.cajero.ordenes.adapters;

import java.util.ArrayList;

import com.cajero.ordenes.estructuras.Orden;
import com.cajero.ordenes.R;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
    	
    	// Rescatamos cada item del listview y lo inflamos con nuestro layout
    LayoutInflater inflater = context.getLayoutInflater();
    View item = inflater.inflate(R.layout.orden_item, null);

 // Definimos los elementos que tiene nuestro layout
    TextView nombre_orden = (TextView)item.findViewById(R.id.orden_item);//este es campo de texto
    TextView numero_mesa = (TextView)item.findViewById(R.id.orden_item_mesa);//este es campo de texto
   // ImageView imagen= (ImageView)item.findViewById(R.id.Estado_imagen);
    LinearLayout heat= (LinearLayout)item.findViewById(R.id.LinearLayout_heat);
    
    Orden elemento = listaOrden.get(position);
   
    //nombre_orden.setText(elemento.getOrden());
    nombre_orden.setText("Orden");
    numero_mesa.setText("Mesa: #"+elemento.getmNumeroMesa());
   
    /*if(elemento.getmEstadoServido().equals("I")){
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
    }*/
    
    if(elemento.getmEstadoServido().equals("I")){
    	heat.setBackgroundColor(Color.parseColor("#FF4444"));
    }
    else{
    	 if(elemento.getmEstadoServido().equals("C")){
    		 heat.setBackgroundColor(Color.parseColor("#FFBB33"));
    	    }
    	 else
    	 {
    		 if(elemento.getmEstadoServido().equals("S")){
    			 heat.setBackgroundColor(Color.parseColor("#99CC00"));
     	    	}
    		 else{
    			 if(elemento.getmEstadoServido().equals("N")){
    				 heat.setBackgroundColor(Color.parseColor("#AA66CC"));
    	    	    }
    			 else{
    				 if(elemento.getmEstadoServido().equals("P")){
    					 heat.setBackgroundColor(Color.parseColor("#33B5E5"));
    	     	    	}
	    			 else
	    			 	{
	    				 heat.setBackgroundColor(Color.parseColor("#FF4444"));
	    			 	}
    			 }
    		 }
    	 }
    }
    
    return(item);
}
	
	
}
