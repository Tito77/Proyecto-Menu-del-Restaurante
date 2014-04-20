package com.moviles.clases;

import java.util.ArrayList;

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
	
	public int calcularTotal()
	{
		int iTotal = 0;
		
		for(int i = 0; i<listaPlatillos.size(); i++)
		{
			Platillo temp = listaPlatillos.get(i).getmPlatillo();
			iTotal += Integer.parseInt(temp.get_sPrecio());
		}
		
		return iTotal;
	}
	
	public void agregarPlatillo(PlatilloXOrden pla){
		
		this.listaPlatillos.add(pla);
		
	}
	
	
	

}
