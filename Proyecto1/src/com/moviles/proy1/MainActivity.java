package com.moviles.proy1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.moviles.proyecto1.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);*/
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.container, new ListaMenuFragment(), "lista");
		trans.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if(id == R.id.action_ordenar)
		{
			Intent intOrden = new Intent(this, VerOrdenActivity.class);
			startActivity(intOrden);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
