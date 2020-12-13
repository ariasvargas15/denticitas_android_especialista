package com.amongusdev.especialista.app.citas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.citas.interfaces.OnClickListenerCita;
import com.amongusdev.especialista.model.entities.Cita;
import com.amongusdev.especialista.model.entities.Persona;
import com.amongusdev.especialista.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CitaAdapter extends RecyclerView.Adapter<CitaAdapter.CitaViewHolder> {

    ArrayList<Cita> citas;
    Context context;
    OnClickListenerCita onClick;

    public CitaAdapter(ArrayList<Cita> citas, Context context) {
        this.citas = citas;
        this.context = context;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CitaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cita, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder holder, int position) {
        Cita cita = citas.get(position);

        holder.servicio.setText(cita.getServicio().getNombre());
        holder.especialista.setText(getNombreEspecialista(cita));
        holder.hora.setText(Utils.convert24HourToAmPm(cita.getTurno().getHoraInicio()));

        Calendar fechaCal = getCalendar(cita);
        holder.fecha.setText(Utils.dateToString(fechaCal));

        boolean res = citaPendiente(fechaCal);
        if (!res) holder.delete.setVisibility(View.GONE);
        int drawable = res ? R.drawable.shape_card_cita : R.drawable.shape_card_cita_past;
        int color = res ? R.color.colorPrimary : R.color.White;

        holder.linear.setBackgroundResource(drawable);
        holder.hora.setTextColor(context.getResources().getColor(color, context.getTheme()));
        holder.servicio.setTextColor(context.getResources().getColor(color, context.getTheme()));
        holder.especialista.setTextColor(context.getResources().getColor(color, context.getTheme()));
    }

    private String getNombreEspecialista(Cita cita) {
        Persona especialista = cita.getCliente().getPersona();
        return especialista.getNombre() + " " + especialista.getApellido();
    }

    private Calendar getCalendar(Cita cita) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, cita.getTurno().getDiaAgenda().getAgenda().getAnio());
        calendar.set(Calendar.MONTH, (cita.getTurno().getDiaAgenda().getAgenda().getMes() - 1));
        calendar.set(Calendar.DATE, cita.getTurno().getDiaAgenda().getDia());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(cita.getTurno().getHoraInicio().substring(0, 2)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(cita.getTurno().getHoraInicio().substring(2, 4)));
        return calendar;
    }

    private boolean citaPendiente(Calendar fechaCal) {
        return Calendar.getInstance().before(fechaCal);
    }

    public void setOnClick(OnClickListenerCita onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    class CitaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fecha_cita)
        TextView fecha;
        @BindView(R.id.servicio_cita)
        TextView servicio;
        @BindView(R.id.hora_cita)
        TextView hora;
        @BindView(R.id.especialista_cita)
        TextView especialista;
        @BindView(R.id.linear_cita)
        LinearLayout linear;
        @BindView(R.id.btn_delete)
        ImageView delete;

        public CitaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            delete.setOnClickListener(v->onClick.addEvolucionHistoria(citas.get(getAdapterPosition())));
        }
    }
}
