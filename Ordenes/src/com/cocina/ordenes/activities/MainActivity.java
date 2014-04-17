package com.cocina.ordenes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Button;

import com.cocina.ordenes.R;
import com.cocina.ordenes.estructuras.Orden;
import com.cocina.ordenes.fragments.DetalleFragment;
import com.cocina.ordenes.fragments.ListadoFragment;
import com.cocina.ordenes.fragments.ListadoFragment.OrdenListener;

public class MainActivity extends FragmentActivity implements OrdenListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//if(findViewById(R.id.contenedor)!=null){//en caso de que estamos en un móvil
			//ListadoFragment primerfragmento= new ListadoFragment();
			ListadoFragment primerfragmento= (ListadoFragment)getSupportFragmentManager().findFragmentById(R.id.frag_listado);;
			primerfragmento.setOrdenListener(this);
			//getSupportFragmentManager().beginTransaction().add(R.id.contenedor, primerfragmento).commit();
		//}
		//e.o.c se esta ejecutando en una tablet
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	public void onOrdenSeleccionado(Orden c) {   // este metodo hace posible saber si se selecciono un item de la lista
												// a la vez ejecuta que se cargue el texto en el campo designado
        boolean hayDetalle =
            (getSupportFragmentManager().findFragmentById(R.id.frag_detalle) != null);
 
        if(hayDetalle) {
            ((DetalleFragment)getSupportFragmentManager()
                .findFragmentById(R.id.frag_detalle)).mostrarDetalle(c.getDe());
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getTexto());
            startActivity(i);
        }
    }
	

}
