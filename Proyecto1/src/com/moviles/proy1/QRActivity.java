package com.moviles.proy1;

import com.moviles.proyecto1.R;
import com.moviles.proyecto1.R.layout;
import com.moviles.proyecto1.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QRActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr);
		
		Button generate= (Button)findViewById(R.id.Button_QR);
        
        generate.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
            	Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
            	intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
            	EditText mtexto=(EditText) findViewById(R.id.cajadetexto);
            	
            	intent.putExtra("ENCODE_DATA", mtexto.getText().toString());//aqui va el texto
            	startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qr, menu);
		return true;
	}

}
