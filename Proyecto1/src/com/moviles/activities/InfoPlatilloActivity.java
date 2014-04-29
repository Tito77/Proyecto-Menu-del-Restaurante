package com.moviles.activities;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moviles.adapters.IngredienteAdapter;
import com.moviles.clases.Platillo;
import com.moviles.proy1.CustomContext;
import com.moviles.proyecto1.R;

public class InfoPlatilloActivity extends Activity {
	
	int posPlatillo = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info_platillo);
		setTitle("Detalles de platillo");
		
		posPlatillo = getIntent().getExtras().getInt("posicion");
		final Platillo seleccionado = ((CustomContext)getApplicationContext()).lPlatillos.get(posPlatillo);
		
		IngredienteAdapter _adapter = new IngredienteAdapter(this, seleccionado.get_lIngredientes());
		
		((TextView)findViewById(R.id.txNombre)).setText(seleccionado.get_sNombre());
		((TextView)findViewById(R.id.txPrecio)).setText("Precio: ₡"+seleccionado.get_sPrecio());
		((ListView)findViewById(R.id.listIngredientes)).setAdapter(_adapter);
		
		new DownloadImageTask((ImageView) findViewById(R.id.ivImagen))
        .execute(seleccionado.getmURLImagen());
		
		
		findViewById(R.id.buttonAgregar2).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InfoPlatilloActivity.this, AgregarAOrdenActivity.class);
				intent.putExtra("posicion", posPlatillo);
				startActivity(intent);
			}
		});
		
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  @Override
		protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  @Override
		protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}

}
