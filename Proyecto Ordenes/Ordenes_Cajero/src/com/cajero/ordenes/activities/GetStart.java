package com.cajero.ordenes.activities;

import com.cajero.ordenes.R;
import com.cajero.ordenes.R.layout;
import com.cajero.ordenes.R.menu;
import com.cajero.ordenes.adapters.AdaptadorImagen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;

public class GetStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_start);
		
		Gallery gallery=(Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new AdaptadorImagen(this));

		Button boton=(Button) findViewById(R.id.button1Comenzar);
		boton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
                Intent intent = new Intent(GetStart.this,MainActivity.class);
                startActivity(intent);
            	finish();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_start, menu);
		return true;
	}

}
