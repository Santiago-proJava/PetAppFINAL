package com.example.petapp.features.weight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import java.util.List;

public class WeightRecordAdapter extends RecyclerView.Adapter<WeightRecordAdapter.ViewHolder> {

    private List<WeightRecord> records;
    private OnItemClickListener onItemClickListener;

    public WeightRecordAdapter(List<WeightRecord> records, OnItemClickListener onItemClickListener) {
        this.records = records;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weight_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeightRecord record = records.get(position);
        holder.tvDate.setText(record.getDate());
        holder.tvWeight.setText(record.getWeight() + " kg");
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(record));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate;
        public TextView tvWeight;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvWeight = itemView.findViewById(R.id.tvWeight);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(WeightRecord record);
    }
}

