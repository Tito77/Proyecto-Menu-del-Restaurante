package com.moviles.clases;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<Platillo> _lPlatillos;
	private String _sNombre, _sDescripcion, _sFechaInicio, _sFechaFin, _sEstado;
	
	
	public ArrayList<Platillo> get_lPlatillos() {
		return _lPlatillos;
	}
	public void set_lPlatillos(ArrayList<Platillo> _lPlatillos) {
		this._lPlatillos = _lPlatillos;
	}
	public String get_sNombre() {
		return _sNombre;
	}
	public void set_sNombre(String _sNombre) {
		this._sNombre = _sNombre;
	}
	public String get_sDescripcion() {
		return _sDescripcion;
	}
	public void set_sDescripcion(String _sDescripcion) {
		this._sDescripcion = _sDescripcion;
	}
	public String get_sFechaInicio() {
		return _sFechaInicio;
	}
	public void set_sFechaInicio(String _sFechaInicio) {
		this._sFechaInicio = _sFechaInicio;
	}
	public String get_sFechaFin() {
		return _sFechaFin;
	}
	public void set_sFechaFin(String _sFechaFin) {
		this._sFechaFin = _sFechaFin;
	}
	public String get_sEstado() {
		return _sEstado;
	}
	public void set_sEstado(String _sEstado) {
		this._sEstado = _sEstado;
	}
	
	

}
