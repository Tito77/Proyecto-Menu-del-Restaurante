package com.moviles.clases;

import java.util.ArrayList;



public class Platillo {
	
	private String _sNombre, _sPrecio, _sLlave;
	private ArrayList<Ingrediente> _lIngredientes;
	public String get_sNombre() {
		return _sNombre;
	}
	public void set_sNombre(String _sNombre) {
		this._sNombre = _sNombre;
	}
	public String get_sPrecio() {
		return _sPrecio;
	}
	public void set_sPrecio(String _sPrecio) {
		this._sPrecio = _sPrecio;
	}
	public String get_sLlave() {
		return _sLlave;
	}
	public void set_sLlave(String _sLlave) {
		this._sLlave = _sLlave;
	}
	public ArrayList<Ingrediente> get_lIngredientes() {
		return _lIngredientes;
	}
	public void set_lIngredientes(ArrayList<Ingrediente> _lIngredientes) {
		this._lIngredientes = _lIngredientes;
	}
	
	

}
