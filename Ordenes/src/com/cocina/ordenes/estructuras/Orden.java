package com.cocina.ordenes.estructuras;

public class Orden {
	
	    private String id;
	    private String orden;
	    private String texto;
	 
	    public Orden(String de, String orden, String texto){
	        this.id = de;
	        this.orden = orden;
	        this.texto = texto;
	    }
	 
	    public String getDe(){
	        return id;
	    }
	 
	    public String getOrden(){
	        return orden;
	    }
	 
	    public String getTexto(){
	        return texto;
	    }
	
}
