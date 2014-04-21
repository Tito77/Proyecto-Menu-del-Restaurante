package com.moviles.proy1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.moviles.proyecto1.R;
import com.moviles.proyecto1.R.id;
import com.moviles.proyecto1.R.layout;
import com.moviles.proyecto1.R.menu;

public class InfoMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_menu);
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.platillosContainer, new ListaPlatillosFragment(), "lista2");
		trans.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.info_menu, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if(id == R.id.action_ordenar2)
		{
			Intent intOrden = new Intent(this, VerOrdenActivity.class);
			startActivity(intOrden);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
