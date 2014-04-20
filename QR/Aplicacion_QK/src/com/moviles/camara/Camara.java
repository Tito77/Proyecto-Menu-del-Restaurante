package com.moviles.camara;

import com.moviles.proyecto1.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
@SuppressLint("CutPasteId")
public class Camara extends Activity {

	private Button scan;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        
       // View inflado=View.inflate(this, R.layout.activity_camara, null);
        
        
        scan= (Button)findViewById(R.id.Button_QR);
        
        scan.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
        
        Button generate= (Button)findViewById(R.id.Button_Generate);
        
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
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
 	   if (requestCode == 0) {
 	      if (resultCode == RESULT_OK) {
 	    	 String contents = intent.getStringExtra("SCAN_RESULT");
             String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
 	         // Handle successful scan
	 	       /* if(contents.equals("http://androideity.com")){
	 	     	Intent i = new Intent(Camara.this, Respuesta.class);
	 	     	startActivity(i);
	 	        	
	 	        }else{
	 	     	//En caso de que sea otro link...
	 	        		              
	 	        }
 	         */
 	      }
 	      else if (resultCode == RESULT_CANCELED) {
 	         // Handle cancel
 	    	  Log.i("App","Scan unsuccessful");
 	      }
 	   }
 	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}


