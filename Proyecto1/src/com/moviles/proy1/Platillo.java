package com.moviles.proy1;

import java.util.ArrayList;


public class Platillo {
	
	private String _sNombre;
	private float _fPrecio;
	private ArrayList<Ingrediente> _lIngredientes;
	
	public String get_sNombre() {
		return _sNombre;
	}
	public void set_sNombre(String _sNombre) {
		this._sNombre = _sNombre;
	}
	public float get_fPrecio() {
		return _fPrecio;
	}
	public void set_fPrecio(float _fPrecio) {
		this._fPrecio = _fPrecio;
	}
	public ArrayList<Ingrediente> get_lIngredientes() {
		return _lIngredientes;
	}
	public void set_lIngredientes(ArrayList<Ingrediente> _lIngredientes) {
		this._lIngredientes = _lIngredientes;
	}
	
	

}
