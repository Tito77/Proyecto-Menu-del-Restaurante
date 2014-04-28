package com.moviles.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.moviles.fragments.ListaMenuFragment;
import com.moviles.proyecto1.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setTitle("La Cuchara Feliz");
		
		Button ordenButton = (Button)findViewById(R.id.verOrdenButton);
		ordenButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VerOrdenActivity.class);
				startActivity(intent);
			}
		});
		
		Button promoButton = (Button)findViewById(R.id.canjearPromoButton);
		promoButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
			}
		});
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.container, new ListaMenuFragment(), "lista");
		trans.commit();
	}

}
