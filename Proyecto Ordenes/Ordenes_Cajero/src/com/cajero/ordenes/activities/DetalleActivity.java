package com.cajero.ordenes.activities;

import com.cajero.ordenes.fragments.DetalleFragment;
import com.cajero.ordenes.R;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetalleActivity extends FragmentActivity {

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle, menu);
		return true;
	}*/
	
	 public static final String EXTRA_TEXTO ="com.cocina.ordenes.activities.EXTRA_TEXTO";
		 
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_detalle);
		 
		        DetalleFragment detalle =
		            (DetalleFragment)getSupportFragmentManager()
		                .findFragmentById(R.id.frag_detalle);
		 
		        detalle.mostrarDetalle(
		            getIntent().getStringExtra(EXTRA_TEXTO));
		    }

}
