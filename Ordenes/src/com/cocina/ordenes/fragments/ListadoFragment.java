package com.cocina.ordenes.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cocina.ordenes.R;
import com.cocina.ordenes.adapters.AdaptadorListaOrdenes;
import com.cocina.ordenes.estructuras.DetalleOrden;
import com.cocina.ordenes.estructuras.ListadeOrdenes;
import com.cocina.ordenes.estructuras.Orden;
import com.cocina.ordenes.miscelaneas.SwipeListViewTouchListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class ListadoFragment extends Fragment {

	public ListadoFragment() {
		// Required empty public constructor
	}
	public ListadeOrdenes listas=new ListadeOrdenes();
	/*private Orden[] datos =  //estos son elementos estáticos que se utilizaran en el ListadoFragment
            listas.getListaOrdenes();*/
 
	
	private ArrayList<Orden> datos =  //estos son elementos estáticos que se utilizaran en el ListadoFragment
           listas.getListaconOrdenes();
	
	
	 private ListView ListaOrdenes;
	 private OrdenListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_listado, container, false); //framento_listado es donde estan las listas
	}
	
	@Override
    public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
        super.onActivityCreated(state);
 
        ListaOrdenes = (ListView)getView().findViewById(R.id.Listado_Ordenes); //Listado_Ordenes es en el fragment_listado y hace referencia a ListaOrdenes
 
        
        // Al adapter personalizado le pasamos el contexto y la lista que contiene	
        final AdaptadorListaOrdenes adapter=new AdaptadorListaOrdenes(this,datos);
        // Añadimos el adapter al listview
        ListaOrdenes.setAdapter(adapter);//se ejecuta el montaje final ya con los datos
        
        ListaOrdenes.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				if (listener!=null) {
					listener.onOrdenSeleccionado(
							(Orden)ListaOrdenes.getAdapter().getItem(pos));
				}
			}
		});
        

      //Deslizar item para borrarlo
      		SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(ListaOrdenes,new SwipeListViewTouchListener.OnSwipeCallback() {
      			@Override
      			public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
      				//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
      				/*datos.remove(reverseSortedPositions[0]);
      				adapter.notifyDataSetChanged();
      				Orden Noelementos;
      				if ((listener!=null)&&(datos.isEmpty())) {
      					Noelementos=new Orden("0", "0", "0", "0");// si ya no hay elementos u ordenes
    					listener.onOrdenSeleccionado(Noelementos);
    				}
      				if ((listener!=null)) {
      					Noelementos=new Orden("-1", "0", "0", "0");
    					listener.onOrdenSeleccionado(Noelementos);//si solamente borro una orden pero todavía hay más en espera
    				}*/
      				
      			}

      			@Override
      			public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
      				//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
      				datos.remove(reverseSortedPositions[0]);
      				adapter.notifyDataSetChanged();
      				Orden Noelementos;
      				if ((listener!=null)&&(datos.isEmpty())) {
      					Noelementos=new Orden();// si ya no hay elementos u ordenes
      					Noelementos.setmKeyValue("0");
    					listener.onOrdenSeleccionado(Noelementos);
    				}
      				else{
	      				if ((listener!=null)) {
	      					Noelementos=new Orden();//si solamente borro una orden pero todavía hay más en espera
	      					Noelementos.setmKeyValue("-1");
	    					listener.onOrdenSeleccionado(Noelementos);
	    				}
      				}
      			}
      		},true, false);

      		//Escuchadores del listView
      		ListaOrdenes.setOnTouchListener(touchListener);
      		ListaOrdenes.setOnScrollListener(touchListener.makeScrollListener());
        
        
        
        
    }
		    
    public interface OrdenListener {
		void onOrdenSeleccionado(Orden c);
	}

	public void setOrdenListener(OrdenListener listener) {
		this.listener=listener;
	}

}
