package com.amongusdev.especialista.app.historia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.amongusdev.especialista.R;
import com.amongusdev.especialista.model.entities.Evolucion;
import com.amongusdev.especialista.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvolucionAdapter extends RecyclerView.Adapter<EvolucionAdapter.EvolucionViewHolder>{

    ArrayList<Evolucion> historia;
    Context context;

    public EvolucionAdapter(ArrayList<Evolucion> historia, Context context){
        this.historia = historia;
        this.context = context;
    }

    @NonNull
    @Override
    public EvolucionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EvolucionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historia, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EvolucionViewHolder holder, int position) {
        Evolucion evolucion = historia.get(position);

        holder.descripcion.setText(evolucion.getDescripcion());

        Calendar fechaCal = Calendar.getInstance();
        fechaCal.setTime(evolucion.getFecha());
        holder.fecha.setText(Utils.dateToString(fechaCal));

        int drawable = R.drawable.shape_card_cita_past;
        int color = R.color.White;

        holder.linear.setBackgroundResource(drawable);
        holder.descripcion.setTextColor(context.getResources().getColor(color, context.getTheme()));
    }

    @Override
    public int getItemCount() {
        return historia.size();
    }

    class EvolucionViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.linear_cita)
        LinearLayout linear;
        @BindView(R.id.fecha)
        TextView fecha;
        @BindView(R.id.descripcion)
        TextView descripcion;

        public EvolucionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
