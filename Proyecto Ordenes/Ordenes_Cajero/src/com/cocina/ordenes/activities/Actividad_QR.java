package com.cocina.ordenes.activities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cocina.ordenes.R;
import com.cocina.ordenes.R.layout;
import com.cocina.ordenes.R.menu;
import com.cocina.ordenes.fragments.ListadoFragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad_QR extends FragmentActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad__qr);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			
		Bundle parametros = this.getIntent().getExtras(); //Definimos el contenedor de parametros
        String t_Total = parametros.getString("Total"); //Guardamos el parametro nombre en la variable nombre
        TextView campo_texto = (TextView) findViewById(R.id.textTotal);
       campo_texto.setText("El total actual es: "+t_Total); //Mostramos el texto
		
       String t_mesa = parametros.getString("Mesa"); //Guardamos el parametro nombre en la variable nombre
       TextView campo_texto_t_mesa = (TextView) findViewById(R.id.textMesa);
       campo_texto_t_mesa.setText("Numero de Mesa: "+t_mesa); //Mostramos el texto
       
        final String idorden = parametros.getString("IDorden"); //Guardamos el parametro nombre en la variable nombre
       
       valor_total=Integer.parseInt(t_Total);
       	TextView textodescuento= (TextView)findViewById(R.id.textTotalDes);
  	 	textodescuento.setText("El total con descuento es: "+0);
       
		Button scan= (Button)findViewById(R.id.Button_QR);
        
			scan.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent, 0);
            }
        });
			Button cancel= (Button)findViewById(R.id.Bcancelar);
	        
			cancel.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub  
            		//Intent i=new Intent(Actividad_QR.this, MainActivity.class);
			        //startActivity(i);
			        finish();
            }
        });	
			
			Button b_apply= (Button)findViewById(R.id.Baply);
	        
			b_apply.setOnClickListener(new View.OnClickListener() {
             
	            HttpGet _getOrden;
				HttpClient httpClient = new DefaultHttpClient();
				String url="";
				@Override
	            public void onClick(View v) {
	                // TODO Auto-generated method stub  
					url="http://solid-clarity-553.appspot.com/?EXECOP=POR&MOD=GO&GOKEY="+idorden;
	            	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					    StrictMode.setThreadPolicy(policy);
					
					    _getOrden = new HttpGet(url);
					    _getOrden.setHeader("content-type", "application/json");
					    try{
					    	HttpResponse resp = httpClient.execute(_getOrden);
					    	String respStr = EntityUtils.toString(resp.getEntity());
					    	Log.v("ServicioUpdateEstadon",respStr);
					    }
					    catch(Exception ex)
					    {
					        Log.e("ServicioRest","Error!", ex);
					    }
	            	
	            	
	            		//Intent i=new Intent(Actividad_QR.this, MainActivity.class);
				        //startActivity(i);
				        finish();
            }
        });	
			
			
			
			ID_orden= parametros.getString("IDorden"); //Guardamos el parametro nombre en la variable nombre
			
			
	}
	int valor_total;
	int valor_descuento;
	String ID_orden;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad__qr, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
 	   if (requestCode == 0) {
 	      if (resultCode == RESULT_OK) {
 	    	 String contents = intent.getStringExtra("SCAN_RESULT");
             String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
 	         // Handle successful scan
             
             String[] separadas = contents.split("-", 2);
             if(separadas.length!=1){
	             if(separadas[0].equals("T")){//si es amigo
	            	 TextView mensaje= (TextView)findViewById(R.id.TMensajesQR);
	            	 mensaje.setText("El siguiente usuario si es amigo de Facebook :D");
	            	 TextView usuario= (TextView)findViewById(R.id.TUsuariosQR);
	            	 usuario.setText("El usuario es: "+separadas[1]);
	            	 TextView textodescuento= (TextView)findViewById(R.id.textTotalDes);
	            	 valor_descuento=valor_total-(valor_total*5/100);
	            	 valor_total=valor_descuento;
	            	 textodescuento.setText("El total-Descuento es: "+valor_descuento);
	             }
	             if(separadas[0].equals("F")){//no es amigo
	            	 TextView mensaje= (TextView)findViewById(R.id.TMensajesQR);
	            	 mensaje.setText("El siguiente usuario no es amigo de Facebook :(");
	            	 TextView usuario= (TextView)findViewById(R.id.TUsuariosQR);
	            	 usuario.setText("El usuario es: "+separadas[1]);
	            	 TextView mensaje2= (TextView)findViewById(R.id.TMensaje2);
	            	 mensaje2.setText("No aplica en la promoción");
	            	 TextView textodescuento= (TextView)findViewById(R.id.textTotalDes);
	            	 valor_descuento=valor_total;
	            	 textodescuento.setText("El total-Descuento es: "+valor_descuento);
	             }
             }
             else{//es en otro caso
            	 TextView usuario= (TextView)findViewById(R.id.TMensajesQR);
            	 usuario.setText("Usted leyo lo siguiente: ");
            	 TextView mensaje= (TextView)findViewById(R.id.TUsuariosQR);
            	 mensaje.setText(""+separadas[0]);
            	 TextView mensaje2= (TextView)findViewById(R.id.TMensaje2);
            	 mensaje2.setText("No esta incluido en la promoción");
            	 TextView textodescuento= (TextView)findViewById(R.id.textTotalDes);
            	 valor_descuento=valor_total;
            	 textodescuento.setText("El total-Descuento es: "+valor_descuento);
             }
	 	      
 	      }
 	      else if (resultCode == RESULT_CANCELED) {
 	         // Handle cancel
 	    	  Log.i("App","Scan unsuccessful");
 	      }
 	   }
 	}

}
