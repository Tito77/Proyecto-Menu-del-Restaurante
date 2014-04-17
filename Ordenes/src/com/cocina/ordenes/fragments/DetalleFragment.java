package com.cocina.ordenes.fragments;

import com.cocina.ordenes.R;
import com.cocina.ordenes.R.layout;
import com.cocina.ordenes.estructuras.DetalleOrden;
import com.cocina.ordenes.estructuras.ListadeOrdenes;
import com.cocina.ordenes.estructuras.Orden;
import com.cocina.ordenes.fragments.ListadoFragment.AdaptadorOrdenes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class DetalleFragment extends Fragment {

	public DetalleFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View vista = inflater.inflate(R.layout.fragment_detalle, container, false);
		((Button) vista.findViewById(R.id.Finalizado))
        .setOnClickListener(new OnClickListener() {

           @Override
           public void onClick(View v) {
              pulsadoBoton(v);
           }
        });
		return vista;
	}
	 
	public void pulsadoBoton(View v) {
		
		listas.eliminarOrdenLista(posicion_orden);
		
	   }
	
	
	public void mostrarDetalle(String texto) { //nos ayuda posteriormente a asignar el contenido a mostrar en el cuadro de texto
        //TextView txtDetalle =
            //(TextView)getView().findViewById(R.id.test_detalle);
	
		

		
		
		datos=listas.getDetalleOrdenesPlatillos(texto);
		posicion_orden=Integer.parseInt(texto);
		
        //txtDetalle.setText(texto);
		ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
		

		
        ListaPlatillos.setAdapter(new AdaptadorPlatillos(this));//se ejecuta el montaje final ya con los datos
    }


	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ListadeOrdenes listas=new ListadeOrdenes();
	private DetalleOrden[] datos; //=  //estos son elementos estáticos que se utilizaran en el ListadoFragment
            //listas.getDetalleOrdenes();
 
	
	 private ListView ListaPlatillos;
	 int posicion_orden=1;
	 String text_posicion_orden="1";
	 
	 @Override
	 public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
	        super.onActivityCreated(state);
	        datos=listas.getDetalleOrdenesPlatillos(text_posicion_orden);
	        ListaPlatillos = (ListView)getView().findViewById(R.id.Listado_Platillos); //Listado_Platillos es en el fragment_listado y hace referencia a ListaOrdenes
	 
	        ListaPlatillos.setAdapter(new AdaptadorPlatillos(this));//se ejecuta el montaje final ya con los datos
	        
	    
	        
	           
	    }
	    class AdaptadorPlatillos extends ArrayAdapter<DetalleOrden> { //aqui se realiza el motaje del adaptador con los datos,
			//solomente se le dice cuales elementos debe seleccionar
		
	    	Activity context;
		

	    	AdaptadorPlatillos(Fragment context) {
			super(context.getActivity(), R.layout.item_platillo, datos);
			this.context = context.getActivity();
			}
		
			public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.item_platillo, null);
			
			TextView texto_platillos = (TextView)item.findViewById(R.id.platillos);//este es campo de texto
			texto_platillos.setText(datos[position].getPlatillo());
			TextView texto_cantidad = (TextView)item.findViewById(R.id.cantidades);//este es campo de texto
			texto_cantidad.setText(datos[position].getCantidad());
			TextView texto_descripcion = (TextView)item.findViewById(R.id.descripciones);//este es campo de texto
			texto_descripcion.setText(datos[position].getTexto());
			//TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
			//lblAsunto.setText(datos[position].getAsunto());
			/*Button boton=(Button)item.findViewById(R.id.Finalizado);
				boton.setOnClickListener(new View.OnClickListener(){
					public void onClick(View view){
						Button boton2 = (Button) view;
						boton2.setText("Pulsado");
						
					}
					});*/
			return(item);
			}

			
			
			
			
	    }

}
