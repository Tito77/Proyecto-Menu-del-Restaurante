package com.cajero.ordenes.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.cajero.ordenes.estructuras.Orden;
import com.cajero.ordenes.fragments.DetalleFragment;
import com.cajero.ordenes.fragments.ListadoFragment;
import com.cajero.ordenes.fragments.ListadoFragment.OrdenListener;
import com.cajero.ordenes.R;

public class MainActivity extends FragmentActivity implements OrdenListener {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//if(findViewById(R.id.contenedor)!=null){//en caso de que estamos en un móvil
			//ListadoFragment primerfragmento= new ListadoFragment();
			
		//	getSupportFragmentManager().beginTransaction().add(R.id.contenedor, primerfragmento).commit();
		//}
		//e.o.c se esta ejecutando en una tablet
			if (savedInstanceState==null){
				ListadoFragment primerfragmento= (ListadoFragment)getSupportFragmentManager().findFragmentById(R.id.frag_listado);
				primerfragmento.setOrdenListener(this);
			}
			else{
				ListadoFragment primerfragmento= (ListadoFragment)getSupportFragmentManager().getFragment(savedInstanceState, "MiFragment");
				primerfragmento.setOrdenListener(this);
			}
			
		
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	ListadoFragment primerfragmento= (ListadoFragment)getSupportFragmentManager().findFragmentById(R.id.frag_listado);
	getSupportFragmentManager().putFragment(outState, "MiFragment", primerfragmento);
	}
	
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	//este metodo es implementado, pero la clase esta creada en el fragmente ListadoFragment
	@Override
	public void onOrdenSeleccionado(Orden c) {   // este metodo hace posible saber si se selecciono un item de la lista
												// a la vez ejecuta que se cargue el texto en el campo designado
        boolean hayDetalle =
            (getSupportFragmentManager().findFragmentById(R.id.frag_detalle) != null);
 
        if(hayDetalle) {
            ((DetalleFragment)getSupportFragmentManager()
                .findFragmentById(R.id.frag_detalle)).mostrarDetalle(c.getmKeyValue());
        }
       /* else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getmTotal());
            startActivity(i);
        }*/
    }



	

}
