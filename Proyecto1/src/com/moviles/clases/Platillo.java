package com.moviles.clases;

import java.util.ArrayList;



public class Platillo {
	
	//"{\n \"mPrecio\": \"\", \n \"mKeyValue\": \"5757334940811264\", \n \"mNombrePlatillo\": \"Arroz Con Camarones\"\n}"
	
	private String mPrecio,mURLImagen,mKeyValue,mNombrePlatillo;
	private ArrayList<Ingrediente> _lIngredientes;
	public String get_sNombre() {
		return mNombrePlatillo;
	}
	public void set_sNombre(String _sNombre) {
		this.mNombrePlatillo = _sNombre;
	}
	public String get_sPrecio() {
		return mPrecio;
	}
	public void set_sPrecio(String _sPrecio) {
		this.mPrecio = _sPrecio;
	}
	public String get_sLlave() {
		return mKeyValue;
	}
	public void set_sLlave(String _sLlave) {
		this.mKeyValue = _sLlave;
	}
	public ArrayList<Ingrediente> get_lIngredientes() {
		return _lIngredientes;
	}
	public void set_lIngredientes(ArrayList<Ingrediente> _lIngredientes) {
		this._lIngredientes = _lIngredientes;
	}
	public String getmURLImagen() {
		return mURLImagen;
	}
	public void setmURLImagen(String mURLImagen) {
		this.mURLImagen = mURLImagen;
	}
	
	

}
