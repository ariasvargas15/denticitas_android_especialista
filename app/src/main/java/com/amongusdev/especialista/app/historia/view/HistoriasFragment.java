package com.amongusdev.especialista.app.historia.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.historia.adapter.EvolucionAdapter;
import com.amongusdev.especialista.app.historia.interfaces.IHistoria;
import com.amongusdev.especialista.app.historia.presenter.HistoriaPresenter;
import com.amongusdev.especialista.model.entities.Evolucion;
import com.amongusdev.especialista.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class HistoriasFragment extends Fragment implements IHistoria.View {


    @BindView(R.id.recycler_historia)
    RecyclerView recycler;
    @BindView(R.id.historia)
    NestedScrollView historia;
    @BindView(R.id.input_cedula)
    TextInputEditText doc;

    IHistoria.Presenter presenter;
    AlertDialog dialog;
    EvolucionAdapter adapter;
    ArrayList<Evolucion> evolucion;

    public HistoriasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_historias, container, false);
        ButterKnife.bind(this, v);
        presenter = new HistoriaPresenter(this);
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();

        return v;
    }


    @Override
    public void setHistoria(ArrayList<Evolucion> citas) {
        if (citas == null || citas.isEmpty()){
            Snackbar.make(historia, "No hay citas registradas para este paciente", Snackbar.LENGTH_LONG).show();
        } else {
            evolucion = citas;
            adapter = new EvolucionAdapter(evolucion, getContext());
            LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(lm);
            recycler.setAdapter(adapter);
        }
        dialog.dismiss();
    }

    @OnClick(R.id.btn_buscar)
    public void buscarHistoria(){
        if (doc.getText().toString().isEmpty()){
            Snackbar.make(historia, "Digite el numero de cedula a buscar", Snackbar.LENGTH_LONG).show();
        } else {
            dialog.show();
            presenter.getHistoria(doc.getText().toString());
        }
    }
}