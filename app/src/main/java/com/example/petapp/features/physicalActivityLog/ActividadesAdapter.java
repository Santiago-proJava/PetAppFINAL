package com.example.petapp.features.physicalActivityLog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import java.util.List;

public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ActividadViewHolder> {

    private List<ActividadFisica> listaActividades;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditar(ActividadFisica actividad);
        void onEliminar(ActividadFisica actividad);
    }

    public ActividadesAdapter(List<ActividadFisica> listaActividades, OnItemClickListener listener) {
        this.listaActividades = listaActividades;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividad, parent, false);
        return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        final ActividadFisica actividad = listaActividades.get(position);
        holder.tvTipo.setText(actividad.getTipo());
        holder.tvDuracion.setText(actividad.getDuracion());

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEditar(actividad);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEliminar(actividad);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }

    public static class ActividadViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipo, tvDuracion;
        ImageButton btnEditar, btnEliminar;

        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvDuracion = itemView.findViewById(R.id.tvDuracion);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
