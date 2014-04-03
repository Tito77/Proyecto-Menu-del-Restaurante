package com.moviles.proy1;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;


public class CustomContext extends Application {
	
	public List<Platillo> lPlatillos;
	
	public void onCreate()
	{
		super.onCreate();
		lPlatillos = new ArrayList<Platillo>();
		
		ArrayList<Ingrediente> _lIngredientes = new ArrayList<Ingrediente>();
		Platillo _gallo = new Platillo();
		
		Ingrediente _arroz = new Ingrediente();
		_arroz.set_sNombre("Arroz");
		_arroz.set_fCalorias(10);
		_lIngredientes.add(_arroz);
		
		Ingrediente _frijoles = new Ingrediente();
		_frijoles.set_sNombre("Frijoles");
		_frijoles.set_fCalorias(10);
		_lIngredientes.add(_frijoles);
		
		_gallo.set_sNombre("Gallo pinto");
		_gallo.set_lIngredientes(_lIngredientes);
		_gallo.set_fPrecio(1500);
		
		lPlatillos.add(_gallo);
	}

}
