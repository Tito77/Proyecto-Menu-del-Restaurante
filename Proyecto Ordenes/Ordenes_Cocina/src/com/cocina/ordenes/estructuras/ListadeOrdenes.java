package com.cocina.ordenes.estructuras;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;



import android.os.StrictMode;
import android.util.Log;

public class ListadeOrdenes {

	public ListadeOrdenes(){
		//LlenadoListaOrden();
		CreaListaOrdenes();
		if(!ListaconOrdenes.isEmpty()){
		CreaListadePlatillos(ListaconOrdenes.get(0).getmKeyValue());
		}
	}
	
	public ArrayList<Orden> ListaconOrdenes= new ArrayList<Orden>();
	public ArrayList<DetalleOrden> ListaconPlatillos= new ArrayList<DetalleOrden>();
	
	/**********************ListaconOrdenes*******************************************************/

HttpClient httpClient = new DefaultHttpClient();
	HttpGet _getOrden,_getMesa;
	Orden ElementoOrden=new Orden();;
	JSONObject respOrden_JSON;
	JSONArray resp_arrayOrden_JSON;
	
	
	public void CreaListaOrdenes(){
		

		ListaconOrdenes= new ArrayList<Orden>();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		//_getOrden = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=SOD&MOD=GO");
	    _getOrden = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=SOD&MOD=GO");
	    _getOrden.setHeader("content-type", "application/json");
    
	    try
		{
		    HttpResponse resp = httpClient.execute(_getOrden);
		    String respStr = EntityUtils.toString(resp.getEntity());
		    Log.v("ServicioRestOrden",respStr);
		    
		    respOrden_JSON = new JSONObject(respStr);
		    resp_arrayOrden_JSON = respOrden_JSON.getJSONArray("RETURNVALUE");
		    
		    
	       /* ElementoOrden = new Gson().fromJson(resp_arrayOrden_JSON.getString(1), Orden.class);
	        Log.v("Menú cargado",ElementoOrden.getmKeyValue());
	        ListaconOrdenes.add(ElementoOrden);*/
		    
		    
		    
		    int lenth_array=resp_arrayOrden_JSON.length();
	        for(int i=0;i<lenth_array;i++){
	        	Orden ElementoOrden = new Gson().fromJson(resp_arrayOrden_JSON.getString(i), Orden.class);
	        	
	        	_getMesa = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=SEL&MOD=GT&GTKEY="+ElementoOrden.getmKeyMesa());
	        	_getMesa.setHeader("content-type", "application/json");
			        
			        HttpResponse resp2 = httpClient.execute(_getMesa);
			        String respStr2 = EntityUtils.toString(resp2.getEntity());
			        Log.v("ServicioRestMesa",respStr2);
			        JSONObject respMesa_JSON = new JSONObject(respStr2);
			        JSONArray resp_arrayMesa_JSON = respMesa_JSON.getJSONArray("RETURNVALUE");
			        Mesa temp = new Gson().fromJson(resp_arrayMesa_JSON.getString(0), Mesa.class);
	        	ElementoOrden.setmNumeroMesa(temp.getmNumeroMesa());
	        	
		        Log.v("Menú cargado",ElementoOrden.getmKeyValue());
		        ListaconOrdenes.add(ElementoOrden);
		        //CreaListadePlatillos(ElementoOrden.getmKeyValue());
	        }
		}
	    catch(Exception ex)
		{
		        Log.e("ServicioRest","Error!", ex);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Orden> getListaconOrdenes() {
		return (ArrayList<Orden>) ListaconOrdenes.clone();
	}
	
	public void removeListaconOrdenes(String pos_element) {
		int position=Integer.parseInt(pos_element);
		if(!ListaconOrdenes.isEmpty()){
		ListaconOrdenes.remove(position);
		}
	}
	/**********************ListaconPlatillos*******************************************************/
	

	
	@SuppressWarnings("unchecked")
	public ArrayList<DetalleOrden> getListaconPlatillos() {
		return (ArrayList<DetalleOrden>) ListaconPlatillos.clone();
	}
	
	public void removeListaconPlatillos(String pos_element) {
		int position=Integer.parseInt(pos_element);
		if(!ListaconPlatillos.isEmpty()){
		ListaconPlatillos.remove(position);
		}
	}
	
	
	
	
	
	//Convertir el arrreglo a Arraylist, eliminar la orden y volver a convertirlo en arreglo 
	/*public void eliminarOrdenLista(int position){ 
	
		int contOrdenes=datos.length-1;
		Orden[] ListTemp = new Orden[contOrdenes];
		int posicion=position-1;// resto la posicion por el algoritmo inicia de 0 a n-1
		
		 if(0!=contOrdenes){ //si el codigo a eliminar es menor o igual al contador de estudiantes
			 
             for(int i=0; i<=contOrdenes; i++){ //Inicio ciclo para realizar la eliminacion
                 if(i ==posicion){ //si el codigo a aliminar coincide con el codigo que hay en la posicion i
                     continue; //continua con el ciclo
                 }
                 else{
                     if(i < posicion)
                    	 ListTemp[i] = datos[i];//si esl codigo es menor se almacena tal cual                     
                     if(i > posicion){
                         //si el codigo es mayor se le resta para que quede en consecutivo
                    	 ListTemp[i-1] = datos[i]; //se almacena con el nuevo codigo
                     }
                 }                                                                   
             } //Fin ciclo para realizar la eliminacion
             datos=null; //se hacen las modificaciones para los nuevos valores                   
             datos = new Orden[ListTemp.length]; //se vuelve a crear el arreglo 
             for(int i=0; i<=(ListTemp.length-1); i++){ //se almacenan los valores no eliminados
                 datos[i]=ListTemp[i];
             }                            
         }
	}*/
	
	
	
	
	
	
	
	
	
	
	
	HttpGet _getListaPlatillos,_getPlatillos;
	Orden ElementoDetalleOrden=new Orden();;
	JSONObject respDetalleOrden_JSON;
	JSONArray resp_arrayDetalleOrden_JSON;
	
	
	public void CreaListadePlatillos(String Posicion_mKeyValue){
		

		ListaconPlatillos= new ArrayList<DetalleOrden>();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
	    _getListaPlatillos = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=SPL&MOD=GO&GOKEY="+Posicion_mKeyValue);
	    _getListaPlatillos.setHeader("content-type", "application/json");
    
	    try
		{
		    HttpResponse resp = httpClient.execute(_getListaPlatillos);
		    String respStr = EntityUtils.toString(resp.getEntity());
		    Log.v("ServicioRestListaPlatillos",respStr);
		    
		    respDetalleOrden_JSON = new JSONObject(respStr);
		    resp_arrayDetalleOrden_JSON = respDetalleOrden_JSON.getJSONArray("RETURNVALUE");

		    int lenth_array=resp_arrayDetalleOrden_JSON.length();
	        for(int i=0;i<lenth_array;i++){
	        	DetalleOrden ElementoDetalleOrden = new Gson().fromJson(resp_arrayDetalleOrden_JSON.getString(i), DetalleOrden.class);
	        	
	        	_getPlatillos = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SEL&MOD=GP&GPKEY="+ElementoDetalleOrden.getmKeyPlatillo());
	        	_getPlatillos.setHeader("content-type", "application/json");
			        
			        HttpResponse resp2 = httpClient.execute(_getPlatillos);
			        String respStr2 = EntityUtils.toString(resp2.getEntity());
			        Log.v("ServicioRestMesa",respStr2);
			        JSONObject respMesa_JSON = new JSONObject(respStr2);
			        JSONArray resp_arrayMesa_JSON = respMesa_JSON.getJSONArray("RETURNVALUE");
			        Platillo temp = new Gson().fromJson(resp_arrayMesa_JSON.getString(0), Platillo.class);
	        	ElementoDetalleOrden.setmNombrePlatillo(temp.getmNombrePlatillo());
	        	
		        Log.v("Platillo cargado",ElementoDetalleOrden.getmKeyOrden());
		        ListaconPlatillos.add(ElementoDetalleOrden);
		        
	        }
		}
	    catch(Exception ex)
		{
		        Log.e("ServicioRest","Error!", ex);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
