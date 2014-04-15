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

import com.google.gson.Gson;
import com.moviles.clases.Ingrediente;
import com.moviles.clases.Menu;
import com.moviles.clases.Platillo;


public class CustomContext extends Application {
	
	HttpClient httpClient = new DefaultHttpClient();
	HttpGet _getMenu, _getPlatillos, _getIngredientes;
	Menu _mMenu;
	ArrayList<Platillo> lPlatillos;
	JSONObject respJSON, platillosJSON, ingJSON;
	JSONArray respJSON2, platillosJSON2, ingJSON2;
	
	public void onCreate()
	{
		super.onCreate();
		_mMenu = new Menu();
		lPlatillos = new ArrayList<Platillo>();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		_getMenu = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SME&MOD=GM&");
		_getMenu.setHeader("content-type", "application/json");
		
		try
		{
		        HttpResponse resp = httpClient.execute(_getMenu);
		        String respStr = EntityUtils.toString(resp.getEntity());
		        Log.v("ServicioRestMenú",respStr);
		        
		        respJSON = new JSONObject(respStr);
		        respJSON2 = respJSON.getJSONArray("RETURNVALUE");
		        //JSONObject tempJSON = respJSON2.getJSONObject(0);
		        _mMenu = new Gson().fromJson(respJSON2.getString(1), Menu.class);
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

}
