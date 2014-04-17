package com.cocina.ordenes.fragments;

import com.cocina.ordenes.R;
import com.cocina.ordenes.R.layout;
import com.cocina.ordenes.estructuras.ListadeOrdenes;
import com.cocina.ordenes.estructuras.Orden;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class ListadoFragment extends Fragment {

	public ListadoFragment() {
		// Required empty public constructor
	}
	public ListadeOrdenes listas=new ListadeOrdenes();
	private Orden[] datos =  //estos son elementos estáticos que se utilizaran en el ListadoFragment
            listas.getListaOrdenes();
 
	
	 private ListView ListaOrdenes;
	 private OrdenListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_listado, container, false); //framento_listado es donde estan las listas
	}
	
	@Override
    public void onActivityCreated(Bundle state) {  //Cuando se crea el activity
        super.onActivityCreated(state);
 
        ListaOrdenes = (ListView)getView().findViewById(R.id.Listado_Ordenes); //Listado_Ordenes es en el fragment_listado y hace referencia a ListaOrdenes
 
        ListaOrdenes.setAdapter(new AdaptadorOrdenes(this));//se ejecuta el montaje final ya con los datos
        
        ListaOrdenes.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
				if (listener!=null) {
					listener.onOrdenSeleccionado(
							(Orden)ListaOrdenes.getAdapter().getItem(pos));
				}
			}
		});
        

    }
	

 
	    class AdaptadorOrdenes extends ArrayAdapter<Orden> { //aqui se realiza el motaje del adaptador con los datos,
	    													//solomente se le dice cuales elementos debe seleccionar
	 
	            Activity context;
	 
	            AdaptadorOrdenes(Fragment context) {
	                super(context.getActivity(), R.layout.orden_item, datos);
	                this.context = context.getActivity();
	            }
	 
	            public View getView(int position, View convertView, ViewGroup parent) {
	            LayoutInflater inflater = context.getLayoutInflater();
	            View item = inflater.inflate(R.layout.orden_item, null);
	 
	            TextView lblDe = (TextView)item.findViewById(R.id.orden_item);//este es campo de texto
	            lblDe.setText(datos[position].getOrden());
	 
	            //TextView lblAsunto = (TextView)item.findViewById(R.id.LblAsunto);
	            //lblAsunto.setText(datos[position].getAsunto());
	 
	            return(item);
	        }
	    }
	    
	    
    public interface OrdenListener {
		void onOrdenSeleccionado(Orden c);
	}

	public void setOrdenListener(OrdenListener listener) {
		this.listener=listener;
	}

}
