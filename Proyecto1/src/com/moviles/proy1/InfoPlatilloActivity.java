package com.moviles.proy1;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;

import com.moviles.proyecto1.R;

public class InfoPlatilloActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_platillo);
		
		int posPlatillo = getIntent().getExtras().getInt("posicion");
		Platillo seleccionado = ((CustomContext)getApplicationContext()).lPlatillos.get(posPlatillo);
		
		IngredienteAdapter _adapter = new IngredienteAdapter(this, seleccionado.get_lIngredientes());
		
		((TextView)findViewById(R.id.txNombre)).setText(seleccionado.get_sNombre());
		((TextView)findViewById(R.id.txPrecio)).setText(seleccionado.get_fPrecio()+"");
		((ListView)findViewById(R.id.listIngredientes)).setAdapter(_adapter);
	}

}
