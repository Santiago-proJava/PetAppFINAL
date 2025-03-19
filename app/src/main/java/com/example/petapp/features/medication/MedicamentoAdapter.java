package com.example.petapp.features.medication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import java.util.List;

public class MedicamentoAdapter extends RecyclerView.Adapter<MedicamentoAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onEditClick(Medicamento medicamento);
        void onDeleteClick(Medicamento medicamento);
    }

    private List<Medicamento> medicamentos;
    private OnItemClickListener listener;
    private Context context;

    public MedicamentoAdapter(Context context, List<Medicamento> medicamentos, OnItemClickListener listener) {
        this.context = context;
        this.medicamentos = medicamentos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MedicamentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicamento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoAdapter.ViewHolder holder, int position) {
        Medicamento medicamento = medicamentos.get(position);
        holder.tvNombreMascota.setText(medicamento.getNombreMascota());
        holder.tvNombreMedicamento.setText(medicamento.getNombreMedicamento());
        holder.tvDosis.setText("Dosis: " + medicamento.getDosis());
        holder.tvFrecuencia.setText("Frecuencia: " + medicamento.getFrecuencia());

        holder.btnEditar.setOnClickListener(v -> listener.onEditClick(medicamento));
        holder.btnEliminar.setOnClickListener(v -> listener.onDeleteClick(medicamento));
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    public void updateList(List<Medicamento> nuevosMedicamentos) {
        this.medicamentos = nuevosMedicamentos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombreMascota;
        public TextView tvNombreMedicamento;
        public TextView tvDosis;
        public TextView tvFrecuencia;
        public Button btnEditar;
        public Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombreMascota = itemView.findViewById(R.id.tvNombreMascota);
            tvNombreMedicamento = itemView.findViewById(R.id.tvNombreMedicamento);
            tvDosis = itemView.findViewById(R.id.tvDosis);
            tvFrecuencia = itemView.findViewById(R.id.tvFrecuencia);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
