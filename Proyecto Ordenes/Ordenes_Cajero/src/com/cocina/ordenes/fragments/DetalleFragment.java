package com.cocina.ordenes.fragments;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cocina.ordenes.R;
import com.cocina.ordenes.activities.Actividad_QR;
import com.cocina.ordenes.adapters.AdaptadorListaPlatillos;
import com.cocina.ordenes.estructuras.DetalleOrden;
import com.cocina.ordenes.estructuras.ListadeOrdenes;
import com.cocina.ordenes.estructuras.Orden;
import com.google.gson.Gson;

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
		listas=new ListadeOrdenes();
		datos=listas.getListaconPlatillos();
		return vista;
	}
	 
	public void pulsadoBoton(View v) {
		
		//listas.eliminarOrdenLista(posicion_orden);
		
	   }
	
	//resibe el key o ID de la Orden
	public void mostrarDetalle(String texto) { //nos ayuda posteriormente a asignar el contenido a mostrar en el cuadro de texto
        //TextView txtDetalle =
            //(TextView)getView().findViewById(R.id.test_detalle);
		;
    	LinearLayout cuadro1= (LinearLayout) getView().findViewById(R.id.cCuadro1);
		 cuadro1.setVisibility(4);
    	
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
			MontarOrden(texto);
			listas.CreaListadePlatillos(texto);
	        datos=listas.getListaconPlatillos();
	        SetVisibility_Componentes();
	        //txtDetalle.setText(texto);
			ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
			
			// Al adapter personalizado le pasamos el contexto y la lista que contiene	
	        AdaptadorListaPlatillos adapter=new AdaptadorListaPlatillos(this,datos);
	        // Añadimos el adapter al listview
	        ListaPlatillos.setAdapter(adapter);//se ejecuta el montaje final ya con los datoss
	        
		}
			
    }
	
	
	


	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ListadeOrdenes listas;//=new ListadeOrdenes();
	private ArrayList<DetalleOrden> datos; //=  //estos son elementos estáticos que se utilizaran en el ListadoFragment
            //listas.getDetalleOrdenes();
 
	
	 private ListView ListaPlatillos;
	// String posicion_orden=0;
	
	 
	 @Override
	 public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
	        super.onActivityCreated(state);
	        
	        datos=listas.getListaconPlatillos();
	        SetVisibility_Componentes();
	        
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
	         else{
	        	 MontarOrden(datos.get(0).getmKeyOrden());
	         }
	         
	 		
	        ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
	 
	        // Al adapter personalizado le pasamos el contexto y la lista que contiene	
	        AdaptadorListaPlatillos adapter=new AdaptadorListaPlatillos(this,datos);
	        // Añadimos el adapter al listview
	        ListaPlatillos.setAdapter(adapter);//se ejecuta el montaje final ya con los datos
	        
	    	
	    } 

	 public void SetVisibility_Componentes(){
		 
		 LinearLayout cuadro1= (LinearLayout) getView().findViewById(R.id.cCuadro1);
	        if(!datos.isEmpty()){
	        	/*
	        	 	0 is for VISIBLE
					4 is for INVISIBLE 
					8 is for GONE
	        	 */
	        	cuadro1.setVisibility(0);
	        }
	        else{
	        	cuadro1.setVisibility(4);
	        }
	 }
	 
	 
	 HttpClient httpClient = new DefaultHttpClient();
		HttpGet _getOrden;
		String url="http://infra-oath-557.appspot.com/";
		Orden ElementoOrden;
		JSONObject respOrden_JSON;
    	JSONArray resp_arrayOrden_JSON;
	 /*public void MontarOrden()
	 {  String Key_orden=datos.get(0).getmKeyOrden();
	 
		 TextView texto_mesa= (TextView) getView().findViewById(R.id.tMesa);
		// texto_mesa.setText(text)
		 url="http://solid-clarity-553.appspot.com/?EXECOP=SEL&MOD=GO&GOKEY="+Key_orden;
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		
		    _getOrden = new HttpGet(url);
		    _getOrden.setHeader("content-type", "application/json");
		    try{
		    	HttpResponse resp = httpClient.execute(_getOrden);
		    	String respStr = EntityUtils.toString(resp.getEntity());
		    	Log.v("ServicioUpdateEstadon",respStr);
		    	JSONObject respOrden_JSON = new JSONObject(respStr);
		    	JSONArray resp_arrayOrden_JSON = respOrden_JSON.getJSONArray("RETURNVALUE");
		    	ElementoOrden = new Gson().fromJson(resp_arrayOrden_JSON.getString(0), Orden.class);
		    	
		    	
		    }
		    catch(Exception ex)
		    {
		        Log.e("ServicioRest","Error!", ex);
		    }
		    texto_mesa.setText(ElementoOrden.g)
		 
	 }*/
    	Orden ElementoX;
    	public void MontarOrden(String key){
    		ArrayList<Orden> lista_orden=listas.getListaconOrdenes();
    		Iterator<Orden> nombreIterator = lista_orden.iterator();
    		boolean valor=true;
    		
    		//buscamos la orden por el key de los platillos
    		while((nombreIterator.hasNext())&&(valor)){
    			
    			ElementoX = nombreIterator.next();
    			 String nuevo_K=ElementoX.getmKeyValue();
    			if(nuevo_K.equals(key)){
    				valor=false;
    			}
    		}
    		if(valor==false){
    			
    			 TextView T_Mesa= (TextView) getActivity().findViewById(R.id.tMesa);
    			 T_Mesa.setText(ElementoX.getmNumeroMesa());
    			
    			 TextView T_Total= (TextView) getActivity().findViewById(R.id.tTotal);
    			 T_Total.setText("Total: "+ElementoX.getmTotal());
    			 
    		}
    		else{
    			 LinearLayout cuadro1= (LinearLayout) getView().findViewById(R.id.cCuadro1);
    			 cuadro1.setVisibility(4);
    		}
    		
    	}
}
