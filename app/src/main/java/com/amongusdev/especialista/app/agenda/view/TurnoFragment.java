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
import com.amongusdev.especialista.app.agenda.adapter.TurnoAdapter;
import com.amongusdev.especialista.app.agenda.interfaces.OnClickListenerRegistro;
import com.amongusdev.especialista.model.entities.DiaAgenda;
import com.amongusdev.especialista.model.entities.Turno;
import com.amongusdev.especialista.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class TurnoFragment extends Fragment implements OnClickListenerRegistro {

    @BindView(R.id.recycler_turnos)
    RecyclerView recycler;
    @BindView(R.id.fecha_turno)
    TextView fecha;
    @BindView(R.id.turnos)
    LinearLayout turnos;

    private static final String ARG_PARAM1 = "agenda";

    private DiaAgenda diaAgenda;
    AlertDialog dialog;
    NavController navController;
    Calendar myCalendar;

    public TurnoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            diaAgenda = (DiaAgenda) getArguments().getSerializable(ARG_PARAM1);
            myCalendar = (Calendar) getArguments().getSerializable("calendar");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_turno, container, false);
        ButterKnife.bind(this, v);
        fecha.setText(Utils.dateToString(myCalendar).replace("\n", " "));
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        setAdapter();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void setAdapter(){
        TurnoAdapter adapter = new TurnoAdapter((ArrayList<Turno>) diaAgenda.getTurnos(), getActivity());
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter.setOnClick(this);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void crearCita(int idTurno) {
        /*String ced = Utils.getValuePreference(getContext(), "auth");
        dialog.show();
        presenter.createCita(ced, servicio.getId(), idTurno);*/
    }

    @OnClick(R.id.salir_turno)
    public void salir(){
        navController.navigate(R.id.mobile_navigation);
    }

}