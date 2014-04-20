package com.moviles.proy1;

import java.util.ArrayList;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;

import com.moviles.clases.Platillo;
import com.moviles.clases.PlatilloXOrden;
import com.moviles.proyecto1.R;

public class InfoPlatilloActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_platillo);
		setTitle("Detalles de platillo");
		
		//mContext = (CustomContext) this.getApplicationContext();
		
		int posPlatillo = getIntent().getExtras().getInt("posicion");
		final Platillo seleccionado = ((CustomContext)getApplicationContext()).lPlatillos.get(posPlatillo);
		
		IngredienteAdapter _adapter = new IngredienteAdapter(this, seleccionado.get_lIngredientes());
		
		((TextView)findViewById(R.id.txNombre)).setText(seleccionado.get_sNombre());
		((TextView)findViewById(R.id.txPrecio)).setText("Precio: ₡"+seleccionado.get_sPrecio());
		((ListView)findViewById(R.id.listIngredientes)).setAdapter(_adapter);
		
		
		findViewById(R.id.buttonOrdenar).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				PlatilloXOrden platillo = new PlatilloXOrden();
				EditText etCantidad = (EditText)findViewById(R.id.cantidadField);
				EditText etNota = (EditText)findViewById(R.id.notaField);
				String sCantidad = etCantidad.getText().toString();
				String sNota = etNota.getText().toString();
				if(sCantidad.equals(null)){
					platillo.setmCantidad("1");
				}
				else{
					platillo.setmCantidad(sCantidad);
				}
				platillo.setmNotaEspecial(sNota);
				platillo.setmPlatillo(seleccionado);
				insertarPlatillo(platillo);
				finish();
			}
		});
		
	}

	protected void insertarPlatillo(PlatilloXOrden platillo) {
		// TODO Auto-generated method stub
		((CustomContext)this.getApplicationContext())._mOrden.agregarPlatillo(platillo);
	}

}
