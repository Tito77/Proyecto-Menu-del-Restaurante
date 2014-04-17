package com.cocina.ordenes.estructuras;

public class DetalleOrden {
    private String id;
    private String platillo;
    private String texto;
    private String cantidad;
 
    public DetalleOrden(String de, String platillo, String texto,String cantidad){
        this.id = de;
        this.platillo = platillo;
        this.texto = texto;
        this.cantidad = cantidad;
    }
 
    public String getDe(){
        return id;
    }
 
    public String getPlatillo(){
        return platillo;
    }
    
    public String getCantidad(){
        return cantidad;
    }
 
    public String getTexto(){
        return texto;
    }
}
