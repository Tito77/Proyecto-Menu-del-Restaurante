package com.moviles.proy1;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.moviles.clases.Ingrediente;
import com.moviles.clases.Menu;
import com.moviles.clases.Mesa;
import com.moviles.clases.Orden;
import com.moviles.clases.Platillo;
import com.moviles.clases.PlatilloXOrden;


public class CustomContext extends Application {
	
	HttpClient httpClient = new DefaultHttpClient();
	HttpGet _getMenu, _getPlatillos, _getIngredientes, _getMesa;
	Menu _mMenu;
	String llaveOrden, llaveMesa;
	public ArrayList<Platillo> lPlatillos;
	public ArrayList<Menu> lMenus;
	JSONObject respJSON, platillosJSON, ingJSON;
	JSONArray respJSON2, platillosJSON2, ingJSON2;
	public Orden _mOrden;
	private static String numMesa = "1";
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		_mMenu = new Menu();
		lPlatillos = new ArrayList<Platillo>();
		lMenus = new ArrayList<Menu>();
		llaveOrden = new String();
		_mOrden = new Orden();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
	    _getMesa = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=SEL&MOD=GT");
	    _getMesa.setHeader("content-type","application/json");
	    
	    llaveMesa = getMesaKey(_getMesa);
		
		_getMenu = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SME&MOD=GM&");
		_getMenu.setHeader("content-type", "application/json");
		
		try
		{
		        HttpResponse resp = httpClient.execute(_getMenu);
		        String respStr = EntityUtils.toString(resp.getEntity());
		        Log.v("ServicioRestMenú",respStr);
		        
		        respJSON = new JSONObject(respStr);
		        respJSON2 = respJSON.getJSONArray("RETURNVALUE");
		        
		        for(int i=0;i<respJSON2.length();i++){
		        	Menu mTemp = new Menu();
		        	mTemp = new Gson().fromJson(respJSON2.getString(i), Menu.class);
		        	lMenus.add(mTemp);
		        }
		        _mMenu = lMenus.get(1);
		        Log.v("Menú cargado",_mMenu.get_sDescripcion());
		        
		        _getPlatillos = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SPL&MOD=GM&GMKEY="+_mMenu.getmKeyValue());
		        _getPlatillos.setHeader("content-type", "application/json");
		        
		        HttpResponse resp2 = httpClient.execute(_getPlatillos);
		        String respStr2 = EntityUtils.toString(resp2.getEntity());
		        Log.v("ServicioRestPlatillos",respStr2);
		        
		        platillosJSON = new JSONObject(respStr2);
		        platillosJSON2 = platillosJSON.getJSONArray("RETURNVALUE");
		        for(int i=0; i<platillosJSON2.length(); i++)
		        {
		        	Platillo temp = new Gson().fromJson(platillosJSON2.getString(i), Platillo.class);
		        	ArrayList<Ingrediente> lIng = new ArrayList<Ingrediente>();
		        	
		        	_getIngredientes = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SEL&MOD=GI");
		        	_getIngredientes.setHeader("content-type","application/json");
		        	
		        	HttpResponse resp3 = httpClient.execute(_getIngredientes);
			        String respStr3 = EntityUtils.toString(resp3.getEntity());
			        Log.v("ServicioRestIngredientes",respStr3);
			        
			        ingJSON = new JSONObject(respStr3);
			        ingJSON2 = ingJSON.getJSONArray("RETURNVALUE");
			        for(int q=0;q<ingJSON2.length();q++)
			        {
			        	Ingrediente ing = new Gson().fromJson(ingJSON2.getString(q), Ingrediente.class);
			        	lIng.add(ing);
			        }
			        temp.set_lIngredientes(lIng);
			        lPlatillos.add(temp);
		        	Log.v("Platillo cargado",temp.get_sNombre());
		        }
		}
		catch(Exception ex)
		{
		        Log.e("ServicioRest","Error!", ex);
		}
		
	}
	
	public String getOrdenKey() {
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		HttpGet _getOrden2 = new HttpGet("http://infra-oath-557.appspot.com/?EXECOP=INS&MOD=GO&GOTOT="+this._mOrden.getTotal()+"&GTKEY="+llaveMesa);
	    _getOrden2.setHeader("content-type", "application/json");
		String llave = new String();
		
		try
		{
			HttpResponse respOrden = httpClient.execute(_getOrden2);
	        String ordenString = EntityUtils.toString(respOrden.getEntity());
	        Log.v("ServicioRestOrden",ordenString);
	        
	        JSONObject ordenJSON = new JSONObject(ordenString);
	        llave = ordenJSON.getString("RETURNVALUE");
		}
		catch(Exception e)
		{
			Log.e("ServicioRest","Error al cargar llave de orden", e);
		}
		
		return llave;
	}

	private String getMesaKey(HttpGet _getMesa2) {
		String llave = new String();
		
		try
		{
			HttpResponse respMesa = httpClient.execute(_getMesa2);
	        String mesaString = EntityUtils.toString(respMesa.getEntity());
	        Log.v("ServicioRestMesa",mesaString);
	        
	        JSONObject mesaJSON = new JSONObject(mesaString);
	        JSONArray mesaJSON2 = mesaJSON.getJSONArray("RETURNVALUE");
	        for(int i=0; i<mesaJSON2.length(); i++)
	        {
	        	Mesa temp = new Gson().fromJson(mesaJSON2.getString(i), Mesa.class);
	        	if (temp.getmNumeroMesa().equals(numMesa))
	        	{
	        		llave = temp.getmKeyValue();
	        		break;
	        	}
	        	
	        }
		}
		catch(Exception ex)
		{
			Log.e("ServicioRest","Error al cargar llave de mesa",ex);
		}
		return llave;
	}
	
public void enviarOrden(String sLlave) {
	
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    	StrictMode.setThreadPolicy(policy);
		
		_mOrden.setLlave(sLlave);
		
		for(int i=0;i<_mOrden.getListaPlatillos().size();i++){
			
			PlatilloXOrden pxoTemp = _mOrden.getListaPlatillos().get(i);
			Platillo pTemp = pxoTemp.getmPlatillo();
			String sLlaveP = pTemp.get_sLlave();
			String sCant = pxoTemp.getmCantidad();
			String sNota = pxoTemp.getmNotaEspecial();
			sNota.replace(' ', '_');
			String sPromo = pxoTemp.getmNotaPromocion();
			
			HttpGet _getOrden = new HttpGet(
					"http://infra-oath-557.appspot.com/?EXECOP=APL&MOD=GO&GOKEY="+_mOrden.getLlave()
					+"&GPKEY="+sLlaveP
					+"&GPCAN="+sCant
					+"&GONES="+sNota
					+"&GONPR="+sPromo
					);
		    _getOrden.setHeader("content-type", "application/json");
		    
		    try
			{
				HttpResponse respOrden = httpClient.execute(_getOrden);
		        String ordenString = EntityUtils.toString(respOrden.getEntity());
		        Log.v("ServicioRestOrden",ordenString);
		        
		        JSONObject ordenJSON = new JSONObject(ordenString);
		        String result = ordenJSON.getString("RETURNVALUE");
		        Log.v("Resultado de orden",result);
			}
			catch(Exception e)
			{
				Log.e("ServicioRest","Error al enviar orden", e);
				Toast toast = Toast.makeText(this, "Error al enviar la orden, notifique a un mesero.", Toast.LENGTH_SHORT);
				toast.show(); 
			}
			
		}
		Log.v("LLAVE DE ORDEN",_mOrden.getLlave());
		
	}

}
