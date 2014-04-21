package com.cocina.ordenes.fragments;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
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
	
	
	
	
	HttpClient httpClient = new DefaultHttpClient();
	HttpGet _getOrden;

	@Override
    public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
        super.onActivityCreated(state);
 
        ListaOrdenes = (ListView)getView().findViewById(R.id.Listado_Ordenes); //Listado_Ordenes es en el fragment_listado y hace referencia a ListaOrdenes
 
        listas=new ListadeOrdenes();
  		datos= listas.getListaconOrdenes();
  	
        
        
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
      				
      				int pos_item=reverseSortedPositions[0];
      				String url="http://infra-oath-557.appspot.com/";
      				String Estado=datos.get(pos_item).getmEstadoServido();
          			//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
          				if(Estado.equals("S")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("C");
          					url="http://infra-oath-557.appspot.com/?EXECOP=COR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}
          				if(Estado.equals("N")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("S");
          					url="http://infra-oath-557.appspot.com/?EXECOP=SOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}
          				if(Estado.equals("I")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("N");
          					url="http://infra-oath-557.appspot.com/?EXECOP=NOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}	
          				if(Estado.equals("C")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("I");
          					url="http://infra-oath-557.appspot.com/?EXECOP=IOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}	
          					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
          				    StrictMode.setThreadPolicy(policy);
          				
          				    _getOrden = new HttpGet(url);
          				    _getOrden.setHeader("content-type", "application/json");
          				    try{
          				    	HttpResponse resp = httpClient.execute(_getOrden);
          				    	String respStr = EntityUtils.toString(resp.getEntity());
          				    	Log.v("ServicioUpdateEstadon",respStr);
          				    }
          				    catch(Exception ex)
          				    {
          				        Log.e("ServicioRest","Error!", ex);
          				    }
      				
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

      			@Override
      			
      			
      			public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
	
      				int pos_item=reverseSortedPositions[0];
      				String url="http://infra-oath-557.appspot.com/";
      				String Estado=datos.get(pos_item).getmEstadoServido();
          			//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
          				if(Estado.equals("I")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("C");
          					url="http://infra-oath-557.appspot.com/?EXECOP=COR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}
          				if(Estado.equals("C")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("S");
          					url="http://infra-oath-557.appspot.com/?EXECOP=SOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}
          				if(Estado.equals("S")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("N");
          					url="http://infra-oath-557.appspot.com/?EXECOP=NOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}	
          				if(Estado.equals("N")){//si es incompleta la pasa a completa
          					datos.get(pos_item).setmEstadoServido("I");
          					url="http://infra-oath-557.appspot.com/?EXECOP=IOR&MOD=GO&GOKEY="+datos.get(pos_item).getmKeyValue();
          				}	
          					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
          				    StrictMode.setThreadPolicy(policy);
          				
          				    _getOrden = new HttpGet(url);
          				    _getOrden.setHeader("content-type", "application/json");
          				    try{
          				    	HttpResponse resp = httpClient.execute(_getOrden);
          				    	String respStr = EntityUtils.toString(resp.getEntity());
          				    	Log.v("ServicioUpdateEstadon",respStr);
          				    }
          				    catch(Exception ex)
          				    {
          				        Log.e("ServicioRest","Error!", ex);
          				    }
          				
      				
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
      			
      			/*
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
      			 */
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
