package com.amongusdev.especialista.app.agenda.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.agenda.interfaces.IAgenda;
import com.amongusdev.especialista.app.agenda.presenter.AgendaPresenter;
import com.amongusdev.especialista.model.entities.Agenda;
import com.amongusdev.especialista.model.entities.DiaAgenda;
import com.amongusdev.especialista.model.entities.Servicio;
import com.amongusdev.especialista.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class AgendaFragment extends Fragment implements IAgenda.View, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.input_fecha)
    TextInputEditText fecha;
    @BindView(R.id.agenda)
    LinearLayout linear;
    @BindView(R.id.confirmar_fecha)
    TextView button;


    IAgenda.Presenter presenter;
    NavController navController;
    String arg1 = "servicio";
    Servicio servicio;
    Calendar myCalendar = Calendar.getInstance();
    ArrayList<Calendar> diasHabiles = new ArrayList<>();
    ArrayList<Agenda> agenda;
    DiaAgenda diaAgenda;
    AlertDialog dialog;
    public AgendaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            servicio = (Servicio) getArguments().getSerializable(arg1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agenda, container, false);
        ButterKnife.bind(this, v);
        presenter = new AgendaPresenter(this);
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        String cedula = Utils.getValuePreference(getContext(), "auth");
        presenter.getAgendas(cedula);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @OnClick(R.id.confirmar_fecha)
    public void confirmarFecha() {
        getDiaAgenda();
        Bundle b = new Bundle();
        b.putSerializable("agenda", diaAgenda);
        navController.navigate(R.id.action_nav_agendar_to_nav_turno, b);
    }


    @Override
    public void setAgendas(ArrayList<Agenda> agendas) {
        this.agenda = agendas;
        diasHabiles = new ArrayList<>();
        for (Agenda a : agendas){
            for (DiaAgenda d : a.getDiaAgendaList()){
                diasHabiles.add(setDate(a.getAnio(), a.getMes(), d.getDia()));
            }
        }
        if (diasHabiles.isEmpty()){
            fecha.setEnabled(false);
            button.setEnabled(false);
            Snackbar.make(linear, "No tiene trunos programados", Snackbar.LENGTH_LONG).show();
        }
        dialog.dismiss();
    }

    private Calendar setDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);
        return c;
    }

    @OnClick(R.id.input_fecha)
    public void onClickFecha(){
        showDatePicker();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        fecha.setText(sdf.format(myCalendar.getTime()));
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getParentFragmentManager(), "DatePickerDialog");
        Calendar[] d = diasHabiles.toArray(new Calendar[diasHabiles.size()]);
        dpd.setSelectableDays(d);

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DATE, dayOfMonth);
            updateLabel();
    }

    private void getDiaAgenda(){
        for (Agenda a : agenda){
            for (DiaAgenda d : a.getDiaAgendaList()){
                if (a.getAnio() == myCalendar.get(Calendar.YEAR)
                && a.getMes() == myCalendar.get(Calendar.MONTH)
                && d.getDia() == myCalendar.get(Calendar.DATE)){
                    diaAgenda = d;
                    break;
                }
            }
        }
    }


}