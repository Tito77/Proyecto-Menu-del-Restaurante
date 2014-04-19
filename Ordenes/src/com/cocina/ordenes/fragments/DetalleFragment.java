package com.cocina.ordenes.fragments;

import java.util.ArrayList;

import com.cocina.ordenes.R;
import com.cocina.ordenes.R.layout;
import com.cocina.ordenes.adapters.AdaptadorListaOrdenes;
import com.cocina.ordenes.adapters.AdaptadorListaPlatillos;
import com.cocina.ordenes.estructuras.DetalleOrden;
import com.cocina.ordenes.estructuras.ListadeOrdenes;
import com.cocina.ordenes.estructuras.Orden;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class DetalleFragment extends Fragment {

	public DetalleFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View vista = inflater.inflate(R.layout.fragment_detalle, container, false);
		
		return vista;
	}
	 
	public void pulsadoBoton(View v) {
		
		//listas.eliminarOrdenLista(posicion_orden);
		
	   }
	
	
	public void mostrarDetalle(String texto) { //nos ayuda posteriormente a asignar el contenido a mostrar en el cuadro de texto
        //TextView txtDetalle =
            //(TextView)getView().findViewById(R.id.test_detalle);
	
		if((texto.equals("0"))||(texto.equals("-1"))){// si ya no hay elementos u ordenes o si solamente borro una orden pero todavía hay más en espera
			listas.CreaListadePlatillos(texto);
			DetalleOrden Noelementos=new DetalleOrden();
			Noelementos.setmKeyOrden("0");
			Noelementos.setmCantidad("");
			Noelementos.setmKeyOrden("");
			Noelementos.setmKeyPlatillo("");
			Noelementos.setmNombrePlatillo("No hay más Ordenes");
			Noelementos.setmNotaEspecial("");
			Noelementos.setmNotaPromocion("");

			if(texto.equals("0")){ // si ya no hay elementos u ordenes
				datos=new ArrayList<DetalleOrden>();
		        datos.add(Noelementos);
			}
			if(texto.equals("-1")){//si solamente borro una orden pero todavía hay más en espera
				datos=new ArrayList<DetalleOrden>();
			}
			
		
	        //txtDetalle.setText(texto);
			ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
			
			// Al adapter personalizado le pasamos el contexto y la lista que contiene	
	        AdaptadorListaPlatillos adapter=new AdaptadorListaPlatillos(this,datos);
	        // Añadimos el adapter al listview
	        ListaPlatillos.setAdapter(adapter);//se ejecuta el montaje final ya con los datoss
		}
		else{
			
			listas.CreaListadePlatillos(texto);
	        datos=listas.getListaconPlatillos();
		
	        //txtDetalle.setText(texto);
			ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
			
			// Al adapter personalizado le pasamos el contexto y la lista que contiene	
	        AdaptadorListaPlatillos adapter=new AdaptadorListaPlatillos(this,datos);
	        // Añadimos el adapter al listview
	        ListaPlatillos.setAdapter(adapter);//se ejecuta el montaje final ya con los datoss
		}
    }
	
	
	


	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ListadeOrdenes listas=new ListadeOrdenes();
	private ArrayList<DetalleOrden> datos; //=  //estos son elementos estáticos que se utilizaran en el ListadoFragment
            //listas.getDetalleOrdenes();
 
	
	 private ListView ListaPlatillos;
	 int posicion_orden=1;
	
	 
	 @Override
	 public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
	        super.onActivityCreated(state);
	        datos=listas.getListaconPlatillos();
	         if(datos.isEmpty()){ // si ya no hay elementos u ordenes
	        	 DetalleOrden Noelementos=new DetalleOrden();
				Noelementos.setmKeyOrden("0");
				Noelementos.setmCantidad("");
				Noelementos.setmKeyOrden("");
				Noelementos.setmKeyPlatillo("");
				Noelementos.setmNombrePlatillo("No hay más Ordenes");
				Noelementos.setmNotaEspecial("");
				Noelementos.setmNotaPromocion("");
				datos=new ArrayList<DetalleOrden>();
		        datos.add(Noelementos);
	         }

	        
	        ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
	 
	        // Al adapter personalizado le pasamos el contexto y la lista que contiene	
	        AdaptadorListaPlatillos adapter=new AdaptadorListaPlatillos(this,datos);
	        // Añadimos el adapter al listview
	        ListaPlatillos.setAdapter(adapter);//se ejecuta el montaje final ya con los datos
 
	           
	    } 

}
