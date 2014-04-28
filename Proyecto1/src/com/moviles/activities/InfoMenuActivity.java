package com.moviles.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.moviles.fragments.ListaPlatillosFragment;
import com.moviles.proyecto1.R;

public class InfoMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_menu);
		
		Button ordenButton = (Button)findViewById(R.id.verOrden2Button);
		ordenButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InfoMenuActivity.this, VerOrdenActivity.class);
				startActivity(intent);
			}
		});
		
		FragmentManager m = getFragmentManager();
		FragmentTransaction trans = m.beginTransaction();
		trans.add(R.id.platillosContainer, new ListaPlatillosFragment(), "lista2");
		trans.commit();
	}

}
