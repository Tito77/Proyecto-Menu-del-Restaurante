package com.cajero.ordenes.activities;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cajero.ordenes.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actividad_QR extends FragmentActivity {

	@Override
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
  	 	textodescuento.setText("El Total-Descuento es: "+t_Total);
       
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

			Button b_apply= (Button)findViewById(R.id.Baply);
	        
			b_apply.setOnClickListener(new View.OnClickListener() {
             
	            HttpGet _getOrden,gettotal;
				HttpClient httpClient = new DefaultHttpClient();
				String url="";
				@Override
	            public void onClick(View v) {
	                // TODO Auto-generated method stub  
					url="http://infra-oath-557.appspot.com/?EXECOP=POR&MOD=GO&GOKEY="+idorden;
	            	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					    StrictMode.setThreadPolicy(policy);
					
					    _getOrden = new HttpGet(url);
					    _getOrden.setHeader("content-type", "application/json");
					    try{
					    	HttpResponse resp = httpClient.execute(_getOrden);
					    	String respStr = EntityUtils.toString(resp.getEntity());
					    	Log.v("ServicioUpdateEstado",respStr);
					    	
					    	
					    	String url2="http://infra-oath-557.appspot.com/?EXECOP=UPD&MOD=GO&GOKEY="+idorden+"&GOTOT="+valor_total;
					    	gettotal = new HttpGet(url2);
					    	gettotal.setHeader("content-type", "application/json");
					    	HttpResponse resp2 = httpClient.execute(gettotal);
					    	String respStr2 = EntityUtils.toString(resp2.getEntity());
					    	Log.v("ServicioUpdateTotal",respStr2);
					    	
					    	
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
	public void onConfigurationChanged(Configuration newConfig) {
	 super.onConfigurationChanged(newConfig);
	 setContentView(R.layout.activity_actividad__qr);
	}
	
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
 	    	TextView usuario= (TextView)findViewById(R.id.TUsuariosQR);
 	    	TextView mensaje= (TextView)findViewById(R.id.TMensajesQR);
 	    	TextView mensaje2= (TextView)findViewById(R.id.TMensajeaply); 
 	    	TextView textodescuento= (TextView)findViewById(R.id.textTotalDes);
 	    	 String contents = intent.getStringExtra("SCAN_RESULT");
             String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
 	         // Handle successful scan
             
             String[] separadas = contents.split("-", 2);
             if(separadas.length!=1){
	             if(separadas[0].equals("T")){//si es amigo	
	            	 mensaje.setText("El siguiente usuario si es amigo de Facebook :D");
	            	 usuario.setText("El usuario es: "+separadas[1]);
	            	 valor_descuento=valor_total-(valor_total*5/100);
	            	 valor_total=valor_descuento;
	            	 textodescuento.setText("El total-Descuento es: "+valor_descuento);
	             }
	             if(separadas[0].equals("F")){//no es amigo
	            	 mensaje.setText("El siguiente usuario no es amigo de Facebook :(");
	            	 usuario.setText("El usuario es: "+separadas[1]);
	            	 mensaje2.setText("No aplica en la promoción");
	            	 valor_descuento=valor_total;
	            	 textodescuento.setText("El Total-Descuento es: "+valor_descuento);
	             }
             }
             else{//es en otro caso

            	 mensaje.setText("Usted leyo lo siguiente: ");
            	 usuario.setText(""+separadas[0]);
            	 mensaje2.setText("No esta incluido en la promoción");
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
