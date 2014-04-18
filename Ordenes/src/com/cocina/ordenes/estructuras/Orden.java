package com.cocina.ordenes.estructuras;

public class Orden {
	
	    private String id;
	    private String orden;
	    private String texto;
	    private String mesa;
	 
	    public Orden(String de, String orden, String texto,String mesa){
	        this.id = de;
	        this.orden = orden;
	        this.texto = texto;
	        this.mesa = mesa;
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
	    
	    public String getMesa(){
	        return mesa;
	    }
	
}
