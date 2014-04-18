package com.cocina.ordenes.estructuras;

import java.util.ArrayList;
import java.util.Arrays;

public class ListadeOrdenes {

	public ListadeOrdenes(){
		LlenadoListaOrden();
	}
	
	public ArrayList<Orden> ListaconOrdenes= new ArrayList<Orden>();
	public ArrayList<DetalleOrden> ListaconPlatillos= new ArrayList<DetalleOrden>();
	
	/**********************ListaconOrdenes*******************************************************/
	public void LlenadoListaOrden(){  //estos son elementos estáticos que se utilizaran en el ListadoFragment
		ListaconOrdenes.add(new Orden("1", "Orden 1", "Texto de la Orden 1","14"));
		ListaconOrdenes.add(new Orden("2", "Orden 2", "Texto de la Orden 2","4"));
		ListaconOrdenes.add(new Orden("3", "Orden 3", "Texto de la Orden 3","7"));
		ListaconOrdenes.add(new Orden("4", "Orden 4", "Texto de la Orden 4","8"));
		ListaconOrdenes.add(new Orden("5", "Orden 5", "Texto de la Orden 5","VIP"));

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Orden> getListaconOrdenes() {
		return (ArrayList<Orden>) ListaconOrdenes.clone();
	}
	
	public void removeListaconOrdenes(String pos_element) {
		int position=Integer.parseInt(pos_element);
		if(!ListaconOrdenes.isEmpty()){
		ListaconOrdenes.remove(position);
		}
	}
	/**********************ListaconPlatillos*******************************************************/
	
	
	public void LlenadoListaconPlatillos(){  //estos son elementos estáticos que se utilizaran en el ListadoFragment
		ListaconPlatillos.add(new DetalleOrden("1", "Platillo 1", "Texto de notas especiales 1","5"));
		ListaconPlatillos.add(new DetalleOrden("2", "Platillo 2", "Texto de notas especiales 2","3"));
		ListaconPlatillos.add(new DetalleOrden("3", "Platillo 3", "Texto de notas especiales 3","5"));
		ListaconPlatillos.add(new DetalleOrden("4", "Platillo 4", "Texto de notas especiales 4","6"));
		ListaconPlatillos.add(new DetalleOrden("5", "Platillo 5", "Texto de notas especiales 5","3"));

	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<DetalleOrden> getListaconPlatillos() {
		return (ArrayList<DetalleOrden>) ListaconPlatillos.clone();
	}
	
	public void removeListaconPlatillos(String pos_element) {
		int position=Integer.parseInt(pos_element);
		if(!ListaconPlatillos.isEmpty()){
		ListaconPlatillos.remove(position);
		}
	}
	
	
	
	
	//Convertir el arrreglo a Arraylist, eliminar la orden y volver a convertirlo en arreglo 
	/*public void eliminarOrden(int posicion, Orden[] ListaActual){ 
	ArrayList listanuevadeOrdenes = (ArrayList) Arrays.asList(ListaActual); //Convertimos 
	listanuevadeOrdenes.remove(posicion); //Eliminamos la orden concreto, va de 0 a n-1
	ListaActual = (Orden[]) listanuevadeOrdenes.toArray(new Orden[listanuevadeOrdenes.size()]); //Cambiamos el arreglo por el nuevo sin la orden borrada 
	}*/
	
	
	//Convertir el arrreglo a Arraylist, eliminar la orden y volver a convertirlo en arreglo 
	/*public void eliminarOrdenLista(int position){ 
	
		int contOrdenes=datos.length-1;
		Orden[] ListTemp = new Orden[contOrdenes];
		int posicion=position-1;// resto la posicion por el algoritmo inicia de 0 a n-1
		
		 if(0!=contOrdenes){ //si el codigo a eliminar es menor o igual al contador de estudiantes
			 
             for(int i=0; i<=contOrdenes; i++){ //Inicio ciclo para realizar la eliminacion
                 if(i ==posicion){ //si el codigo a aliminar coincide con el codigo que hay en la posicion i
                     continue; //continua con el ciclo
                 }
                 else{
                     if(i < posicion)
                    	 ListTemp[i] = datos[i];//si esl codigo es menor se almacena tal cual                     
                     if(i > posicion){
                         //si el codigo es mayor se le resta para que quede en consecutivo
                    	 ListTemp[i-1] = datos[i]; //se almacena con el nuevo codigo
                     }
                 }                                                                   
             } //Fin ciclo para realizar la eliminacion
             datos=null; //se hacen las modificaciones para los nuevos valores                   
             datos = new Orden[ListTemp.length]; //se vuelve a crear el arreglo 
             for(int i=0; i<=(ListTemp.length-1); i++){ //se almacenan los valores no eliminados
                 datos[i]=ListTemp[i];
             }                            
         }
	}*/
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public void LlenadoListaconPlatillos(String id_Orden){
		int number=Integer.parseInt(id_Orden);
		switch (number) {
		case 1:
			ListaconPlatillos.clear();
                ListaconPlatillos.add(new DetalleOrden("1", "Platillo 1", "Texto de notas especiales 1","5"));
                ListaconPlatillos.add(new DetalleOrden("2", "Platillo 2", "Texto de notas especiales 2","3"));
                ListaconPlatillos.add(new DetalleOrden("3", "Platillo 3", "Texto de notas especiales 3","5"));
                ListaconPlatillos.add(new DetalleOrden("4", "Platillo 4", "Texto de notas especiales 4","6"));
                ListaconPlatillos.add(new DetalleOrden("5", "Platillo 5", "Texto de notas especiales 5","3"));
			break;
		case 2:
			ListaconPlatillos.clear();
	                ListaconPlatillos.add(new DetalleOrden("1", "Platillo 21", "Texto de notas especiales 21","5"));
	                ListaconPlatillos.add(new DetalleOrden("2", "Platillo 22", "Texto de notas especiales 22","3"));
	                ListaconPlatillos.add(new DetalleOrden("3", "Platillo 23", "Texto de notas especiales 23","5"));
	                ListaconPlatillos.add(new DetalleOrden("4", "Platillo 24", "Texto de notas especiales 24","6"));
	                ListaconPlatillos.add(new DetalleOrden("5", "Platillo 25", "Texto de notas especiales 25","3"));
			break;
		case 3:
			ListaconPlatillos.clear();
	                ListaconPlatillos.add(new DetalleOrden("1", "Platillo 31", "Texto de notas especiales 31","5"));
	                ListaconPlatillos.add(new DetalleOrden("2", "Platillo 32", "Texto de notas especiales 32","3"));
	                ListaconPlatillos.add(new DetalleOrden("3", "Platillo 33", "Texto de notas especiales 33","5"));
	                ListaconPlatillos.add(new DetalleOrden("4", "Platillo 34", "Texto de notas especiales 34","6"));
	                ListaconPlatillos.add(new DetalleOrden("5", "Platillo 35", "Texto de notas especiales 35","3"));
			break;
		case 4:
			ListaconPlatillos.clear();
	                ListaconPlatillos.add(new DetalleOrden("1", "Platillo 41", "Texto de notas especiales 41","5"));
	                ListaconPlatillos.add(new DetalleOrden("2", "Platillo 42", "Texto de notas especiales 42","3"));
	                ListaconPlatillos.add(new DetalleOrden("3", "Platillo 43", "Texto de notas especiales 43","5"));
	                ListaconPlatillos.add(new DetalleOrden("4", "Platillo 44", "Texto de notas especiales 44","6"));
	                ListaconPlatillos.add(new DetalleOrden("5", "Platillo 45", "Texto de notas especiales 45","3"));
			break;
		case 5:
			ListaconPlatillos.clear();
	                ListaconPlatillos.add(new DetalleOrden("1", "Platillo 51", "Texto de notas especiales 51","5"));
	                ListaconPlatillos.add(new DetalleOrden("2", "Platillo 52", "Texto de notas especiales 52","3"));
	                ListaconPlatillos.add(new DetalleOrden("3", "Platillo 53", "Texto de notas especiales 53","5"));
	                ListaconPlatillos.add(new DetalleOrden("4", "Platillo 54", "Texto de notas especiales 54","6"));
	                ListaconPlatillos.add(new DetalleOrden("5", "Platillo 55", "Texto de notas especiales 55","3"));
			break;

		default:
			ListaconPlatillos.clear();
            ListaconPlatillos.add(new DetalleOrden("1", "Platillo 1", "Texto de notas especiales 1","5"));
            ListaconPlatillos.add(new DetalleOrden("2", "Platillo 2", "Texto de notas especiales 2","3"));
            ListaconPlatillos.add(new DetalleOrden("3", "Platillo 3", "Texto de notas especiales 3","5"));
            ListaconPlatillos.add(new DetalleOrden("4", "Platillo 4", "Texto de notas especiales 4","6"));
            ListaconPlatillos.add(new DetalleOrden("5", "Platillo 5", "Texto de notas especiales 5","3"));
			break;
		}
	}
}
