package com.moviles.proy1;

import com.moviles.proyecto1.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.container, new ListaPlatillosFragment(), "lista");
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
		/*if(id == R.id.action_crear)
		{
			VentanaCrearUsuario fragment = new VentanaCrearUsuario();
			fragment.mListener = (IContactoCreadoListener) getFragmentManager().findFragmentByTag("lista");
			fragment.show(getFragmentManager(), "crear");
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}

}