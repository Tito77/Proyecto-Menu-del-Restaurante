package com.moviles.clases;

import java.util.ArrayList;

import android.util.Log;

public class Orden {
	
	private String llave;
	private int total;
	private ArrayList<PlatilloXOrden> listaPlatillos;
	
	public Orden() {
		this.listaPlatillos = new ArrayList<PlatilloXOrden>();
		this.total = 0;
		this.llave = new String();
	}
	
	public String getLlave() {
		return llave;
	}
	public void setLlave(String llave) {
		this.llave = llave;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public ArrayList<PlatilloXOrden> getListaPlatillos() {
		return listaPlatillos;
	}
	public void setListaPlatillos(ArrayList<PlatilloXOrden> listaPlatillos) {
		this.listaPlatillos = listaPlatillos;
	}
	
	public void calcularTotal()
	{
		int iTotal = 0;
		
		for(int i = 0; i<listaPlatillos.size(); i++)
		{
			Platillo temp = listaPlatillos.get(i).getmPlatillo();
			int cant = Integer.parseInt(listaPlatillos.get(i).getmCantidad());
			int precio = Integer.parseInt(temp.get_sPrecio());
			iTotal = iTotal + (cant * precio);
		}
		setTotal(iTotal);
	}
	
	public void agregarPlatillo(PlatilloXOrden pla){
		
		this.listaPlatillos.add(pla);
		Log.v("Platillo insertado",pla.getmPlatillo().get_sNombre());
		Log.v("Platillo insertado",pla.getmCantidad());
		Log.v("Platillo insertado", "Precio: " + pla.getmPlatillo().get_sPrecio());
		calcularTotal();
		imprimirEstado();
	}
	
	public void quitarPlatillo(int pos){
		this.listaPlatillos.remove(pos);
		calcularTotal();
		imprimirEstado();
	}
	
	public void imprimirEstado(){
		
		Log.v("Total Orden", total+"");
		String platillos = "[ ";
		for(int i = 0; i < listaPlatillos.size(); i++){
			PlatilloXOrden temp = listaPlatillos.get(i);
			Platillo temp2 = temp.getmPlatillo();
			platillos = platillos + temp2.get_sLlave() + " ][ ";
		}
		platillos = platillos + " ]";
		Log.v("Platillos Orden", platillos);
		
	}
	
	
	
	
	

}
