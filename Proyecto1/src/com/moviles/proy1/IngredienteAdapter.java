package com.moviles.proy1;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.moviles.clases.Ingrediente;
import com.moviles.proyecto1.R;

public class IngredienteAdapter extends ArrayAdapter<Ingrediente>{

	public IngredienteAdapter(Context context, ArrayList<Ingrediente> lIngredientes) {
	       super(context, R.layout.ingrediente_view, lIngredientes);
	    }
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       Ingrediente _ing = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingrediente_view, null);
       }
       // Lookup view for data population
       TextView tvNombre = (TextView) convertView.findViewById(R.id.ingredienteNombre);
       TextView tvCalorias = (TextView) convertView.findViewById(R.id.ingredienteCals);
       // Populate the data into the template view using the data object
       tvNombre.setText(_ing.get_sNombre());
       tvCalorias.setText(_ing.get_sCalorias()+" cals");
       // Return the completed view to render on screen
       return convertView;
   }
	
}
