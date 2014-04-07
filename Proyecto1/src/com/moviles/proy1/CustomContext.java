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
	HttpGet _getDatos;
	Menu _mMenu;
	ArrayList<Platillo> lPlatillos;
	JSONObject respJSON;
	JSONArray respJSON2;
	
	public void onCreate()
	{
		super.onCreate();
		_mMenu = new Menu();
		lPlatillos = new ArrayList<Platillo>();
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		_getDatos = new HttpGet("http://modern-door-542.appspot.com/?EXECOP=SEL&MOD=GM");
		_getDatos.setHeader("content-type", "application/json");
		
		try
		{
		        HttpResponse resp = httpClient.execute(_getDatos);
		        String respStr = EntityUtils.toString(resp.getEntity());
		        Log.v("ServicioRestDatos",respStr);
		        
		        respJSON = new JSONObject(respStr);
		        respJSON2 = respJSON.getJSONArray("RETURNVALUE");
		        //lPlatillos = new Gson().fromJson(respJSON2.toString(),Platillo.class);
		        //_eActual = new Gson().fromJson(respJSON2.toString(),Estudiante.class);
		}
		catch(Exception ex)
		{
		        Log.e("ServicioRest","Error!", ex);
		}
		
		
		
		//Datos dummy para pruebas
		//////////////////////////////////
		
		ArrayList<Ingrediente> _lIngredientes = new ArrayList<Ingrediente>();
		Platillo _gallo = new Platillo();
		Platillo _camaron = new Platillo();
		Platillo _bistec = new Platillo();
		
		Ingrediente _arroz = new Ingrediente();
		_arroz.set_sNombre("Arroz");
		_arroz.set_sCalorias("10");
		_lIngredientes.add(_arroz);
		
		Ingrediente _frijoles = new Ingrediente();
		_frijoles.set_sNombre("Frijoles");
		_frijoles.set_sCalorias("10");
		_lIngredientes.add(_frijoles);
		
		_gallo.set_sNombre("Gallo pinto");
		_gallo.set_lIngredientes(_lIngredientes);
		_gallo.set_sPrecio("1500");
		
		lPlatillos.add(_gallo);
		
		_camaron.set_sNombre("Arroz con camarones");
		_camaron.set_lIngredientes(_lIngredientes);
		_camaron.set_sPrecio("2500");
		
		lPlatillos.add(_camaron);
		
		_bistec.set_sNombre("Bistec encebollado");
		_bistec.set_lIngredientes(_lIngredientes);
		_bistec.set_sPrecio("3700");
		
		lPlatillos.add(_bistec);
		
		//////////////////////////////////////
	}

}
