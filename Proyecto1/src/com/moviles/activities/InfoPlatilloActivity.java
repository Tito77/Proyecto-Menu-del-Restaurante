package com.moviles.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.moviles.adapters.IngredienteAdapter;
import com.moviles.clases.Platillo;
import com.moviles.clases.PlatilloXOrden;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class InfoPlatilloActivity extends Activity {
	
	int posPlatillo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_platillo);
		setTitle("Detalles de platillo");
		
		posPlatillo = getIntent().getExtras().getInt("posicion");
		final Platillo seleccionado = ((CustomContext)getApplicationContext()).lPlatillos.get(posPlatillo);
		
		IngredienteAdapter _adapter = new IngredienteAdapter(this, seleccionado.get_lIngredientes());
		
		((TextView)findViewById(R.id.txNombre)).setText(seleccionado.get_sNombre());
		((TextView)findViewById(R.id.txPrecio)).setText("Precio: ₡"+seleccionado.get_sPrecio());
		((ListView)findViewById(R.id.listIngredientes)).setAdapter(_adapter);
		
		
		findViewById(R.id.buttonAgregar).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InfoPlatilloActivity.this, AgregarAOrdenActivity.class);
				intent.putExtra("posicion", posPlatillo);
				startActivity(intent);
			}
		});
		
	}

}
