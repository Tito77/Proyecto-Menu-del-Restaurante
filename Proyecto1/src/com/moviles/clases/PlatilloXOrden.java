package com.moviles.clases;

public class PlatilloXOrden {
	
	private Platillo mPlatillo;
	private String mCantidad;
	private String mNotaEspecial, mNotaPromocion;
	
	public PlatilloXOrden(){
		mPlatillo = new Platillo();
		mCantidad = "0";
		mNotaEspecial = new String();
		mNotaPromocion = new String();
	}
	
	
	public Platillo getmPlatillo() {
		return mPlatillo;
	}
	public void setmPlatillo(Platillo mPlatillo) {
		this.mPlatillo = mPlatillo;
	}
	public String getmCantidad() {
		return mCantidad;
	}
	public void setmCantidad(String mCantidad) {
		this.mCantidad = mCantidad;
	}
	public String getmNotaEspecial() {
		return mNotaEspecial;
	}
	public void setmNotaEspecial(String mNotaEspecial) {
		this.mNotaEspecial = mNotaEspecial;
	}
	public String getmNotaPromocion() {
		return mNotaPromocion;
	}
	public void setmNotaPromocion(String mNotaPromocion) {
		this.mNotaPromocion = mNotaPromocion;
	}
	
	

}
