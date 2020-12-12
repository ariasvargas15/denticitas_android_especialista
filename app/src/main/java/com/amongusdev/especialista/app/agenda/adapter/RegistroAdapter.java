package com.amongusdev.especialista.app.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.agenda.interfaces.OnClickListenerRegistro;
import com.amongusdev.especialista.utils.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder> {

    ArrayList<String> turnos;
    ArrayList<Integer> positions;
    Context context;
    OnClickListenerRegistro onClick;

    public RegistroAdapter(ArrayList<String> turnos, Context context) {
        this.turnos = turnos;
        this.context = context;
        positions = new ArrayList<>();
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegistroViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turno, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        String turno = turnos.get(position);

        String h = Utils.convert24HourToAmPm(turno);
        holder.hora.setText(h);

        String libre = "Agendar";
        int colorLibre = R.color.DarkGray;
        String agendado = "Agendado";
        int colorAgendado = R.color.Green;
        holder.estado.setText(libre);
        holder.estado.setBackgroundColor(context.getResources().getColor(colorLibre, context.getTheme()));
        holder.estado.setEnabled(true);

        holder.estado.setOnClickListener(v -> {
            if(holder.estado.getText().equals("Agendar")){
                holder.estado.setText(agendado);
                holder.estado.setBackgroundColor(context.getResources().getColor(colorAgendado, context.getTheme()));
                positions.add(position);
            } else {
                holder.estado.setText(libre);
                holder.estado.setBackgroundColor(context.getResources().getColor(colorLibre, context.getTheme()));
                positions.remove((Integer)position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }

    public ArrayList<String> getActivos(){
        ArrayList<String> array = new ArrayList<>();
        for (int y : positions){
            array.add(turnos.get(y));
        }
        return array;
    }


    class RegistroViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.estado_turno)
        MaterialButton estado;
        @BindView(R.id.hora_turno)
        TextView hora;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}