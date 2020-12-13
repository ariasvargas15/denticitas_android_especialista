package com.amongusdev.especialista.app.citas.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.citas.adapter.CitaAdapter;
import com.amongusdev.especialista.app.citas.interfaces.ICitas;
import com.amongusdev.especialista.app.citas.interfaces.OnClickListenerCita;
import com.amongusdev.especialista.app.citas.presenter.CitasPresenter;
import com.amongusdev.especialista.model.entities.Cita;
import com.amongusdev.especialista.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class CitasFragment extends Fragment implements ICitas.View, OnClickListenerCita {


    @BindView(R.id.recycler_citas)
    RecyclerView recycler;
    @BindView(R.id.citas)
    NestedScrollView citas;

    ICitas.Presenter presenter;
    AlertDialog dialog;
    String cedula;
    CitaAdapter adapter;
    ArrayList<Cita> citasArray;
    NavController navController;

    public CitasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_citas, container, false);
        ButterKnife.bind(this, v);
        presenter = new CitasPresenter(this);
        cedula = Utils.getValuePreference(getContext(), "auth");
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        presenter.getCitas(cedula);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void setCitas(ArrayList<Cita> citas) {
        citasArray = citas;
        adapter = new CitaAdapter(citasArray, getContext());
        adapter.setOnClick(this);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(lm);
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void addEvolucionHistoria(Cita c) {
        Bundle b = new Bundle();
        b.putSerializable("cita", c);
        navController.navigate(R.id.nav_evolucion, b);
    }
}