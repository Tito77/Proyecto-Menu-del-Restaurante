package com.moviles.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.moviles.clases.Platillo;
import com.moviles.clases.PlatilloXOrden;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class AgregarAOrdenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar_aorden);
		
		int posPlatillo = getIntent().getExtras().getInt("posicion");
		final Platillo seleccionado = ((CustomContext)getApplicationContext()).lPlatillos.get(posPlatillo);
		
		TextView tvNombre = (TextView)findViewById(R.id.tvNombreOrden);
		tvNombre.setText("Nombre: " + seleccionado.get_sNombre());
		
		findViewById(R.id.agregarOrdenButton).setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				PlatilloXOrden platillo = new PlatilloXOrden();
				EditText etCantidad = (EditText)findViewById(R.id.agregarCantidadField);
				EditText etNota = (EditText)findViewById(R.id.agregarNotasEdit);
				String sCantidad = etCantidad.getText().toString();
				String sNota = etNota.getText().toString().replace(' ', '_');
				if(sCantidad.equals(null)){
					platillo.setmCantidad("1");
				}
				else{
					platillo.setmCantidad(sCantidad);
				}
				platillo.setmNotaEspecial(sNota);
				platillo.setmPlatillo(seleccionado);
				insertarPlatillo(platillo);
				
				Toast toast = Toast.makeText(AgregarAOrdenActivity.this, "Platillo agregado a la orden", Toast.LENGTH_SHORT);
				toast.show(); 
				
				finish();
			}
			
		});
	}

	protected void insertarPlatillo(PlatilloXOrden platillo) {
		// TODO Auto-generated method stub
		((CustomContext)this.getApplicationContext())._mOrden.agregarPlatillo(platillo);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.agregar_aorden, menu);
		return true;
	}

}
