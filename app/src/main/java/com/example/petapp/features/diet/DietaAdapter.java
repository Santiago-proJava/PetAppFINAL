package com.example.petapp.features.diet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petapp.R;

import java.util.List;

public class DietaAdapter extends ArrayAdapter<Dieta> {

    public DietaAdapter(Context context, List<Dieta> dietas) {
        super(context, 0, dietas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Dieta dieta = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_dieta, parent, false);
        }

        TextView tvTipoComida = convertView.findViewById(R.id.tvTipoComida);
        TextView tvCantidad = convertView.findViewById(R.id.tvCantidad);
        TextView tvHora = convertView.findViewById(R.id.tvHora);
        Button btnEditar = convertView.findViewById(R.id.btnEditar);
        Button btnEliminar = convertView.findViewById(R.id.btnEliminar);

        tvTipoComida.setText("Tipo de Comida: " + dieta.getTipoComida());
        tvCantidad.setText("Cantidad: " + dieta.getCantidad() + " gramos");
        tvHora.setText("Hora: " + dieta.getHora());

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DietaRepository.deleteDieta(dieta.getId());
                remove(dieta);
                notifyDataSetChanged();
                Toast.makeText(getContext(), "Dieta eliminada", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegistroDietaActivity.class);
                intent.putExtra("dieta", dieta);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}

