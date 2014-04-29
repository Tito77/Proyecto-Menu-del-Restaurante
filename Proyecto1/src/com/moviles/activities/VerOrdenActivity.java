package com.moviles.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles.clases.Orden;
import com.moviles.fragments.ListaOrdenesFragment;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class VerOrdenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_orden);
		setTitle("Detalles de la orden");
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.ordenesContainer, new ListaOrdenesFragment(), "lista3");
		trans.commit();
		
		TextView viewTotal = (TextView)findViewById(R.id.tvTotOrd);
		viewTotal.setText(((CustomContext)this.getApplicationContext())._mOrden.getTotal()+"");
		
		findViewById(R.id.buttonOrdenar2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String llave = pedirLLave();
				enviarOrden(llave);
				limpiarOrden();
				
				Toast toast = Toast.makeText(VerOrdenActivity.this, "Orden enviada!", Toast.LENGTH_SHORT);
				toast.show(); 
				
				finish();
			}
			
			
		});
	}
	
	public void actualizarTotal() {
		
		TextView viewTotal = (TextView)findViewById(R.id.tvTotOrd);
		viewTotal.setText(((CustomContext)this.getApplicationContext())._mOrden.getTotal()+"");
		
	}

	protected void limpiarOrden() {
		// TODO Auto-generated method stub
		((CustomContext)this.getApplicationContext())._mOrden = new Orden();
	}

	protected String pedirLLave() {
		// TODO Auto-generated method stub
		String llave = ((CustomContext)this.getApplicationContext()).getOrdenKey();
		return llave;
	}

	protected void enviarOrden(String llave) {
		
		((CustomContext)this.getApplicationContext()).enviarOrden(llave);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_orden, menu);
		return true;
	}

}
