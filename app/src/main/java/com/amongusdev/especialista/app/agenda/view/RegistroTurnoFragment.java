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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.agenda.adapter.RegistroAdapter;
import com.amongusdev.especialista.app.agenda.interfaces.IRegistro;
import com.amongusdev.especialista.app.agenda.presenter.RegistroPresenter;
import com.amongusdev.especialista.utils.Defines;
import com.amongusdev.especialista.utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;


public class RegistroTurnoFragment extends Fragment implements IRegistro.View {

    @BindView(R.id.recycler_turnos)
    RecyclerView recycler;
    @BindView(R.id.fecha_turno)
    TextView fecha;
    @BindView(R.id.turnos)
    LinearLayout turnos;

    private static final String ARG_PARAM1 = "fecha";
    Calendar myCalendar;
    NavController navController;
    RegistroAdapter adapter;
    IRegistro.Presenter presenter;
    AlertDialog dialog;

    public RegistroTurnoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            myCalendar = (Calendar) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registro_turno, container, false);
        ButterKnife.bind(this, v);
        presenter = new RegistroPresenter(this);
        fecha.setText(Utils.dateToString(myCalendar).replace("\n", " "));
        setAdapter();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @OnClick(R.id.confirmar_turno)
    public void confirmarTurnos(){
        ArrayList<String> registro = adapter.getActivos();
        String ced = Utils.getValuePreference(getContext(), "auth");
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.registrarTurnos(ced, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DATE), registro);
    }

    private void setAdapter(){
        ArrayList<String> a = new ArrayList<>();
        Collections.addAll(a, Defines.turnos);
        adapter = new RegistroAdapter(a, getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
    }

    @Override
    public void success(boolean success) {
        dialog.dismiss();
        String msj = success ? "Turnos regsitrados correctamente" : "Ha ocurrido un error";
        Snackbar.make(turnos, msj, Snackbar.LENGTH_SHORT).show();
        navController.navigate(R.id.mobile_navigation);
    }
}