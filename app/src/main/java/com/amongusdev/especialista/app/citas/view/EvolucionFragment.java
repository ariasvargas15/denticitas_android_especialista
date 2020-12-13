package com.amongusdev.especialista.app.citas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.citas.interfaces.IEvolucion;
import com.amongusdev.especialista.app.citas.presenter.EvolucionPresenter;
import com.amongusdev.especialista.model.entities.Cita;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EvolucionFragment extends Fragment implements IEvolucion.View {

    @BindView(R.id.btn_datos)
    MaterialButton confirmar;
    @BindView(R.id.descripcion)
    TextInputEditText descripcion;
    @BindView(R.id.evolucion)
    ScrollView view;

    Cita cita;
    IEvolucion.Presenter presenter;
    NavController navController;

    public EvolucionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cita = (Cita )getArguments().getSerializable("cita");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_evolucion, container, false);
        ButterKnife.bind(this, v);
        presenter = new EvolucionPresenter(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @OnClick(R.id.btn_datos)
    public void registrarHistoria(){
        if (descripcion.getText().toString().isEmpty()){
            Snackbar.make(view, "La descripción está vacía", Snackbar.LENGTH_SHORT).show();
        } else {
            presenter.setEvolucion(cita, descripcion.getText().toString());
        }
    }

    @Override
    public void showResponse(boolean success) {
        if (success){
            Snackbar.make(view, "Evolución creada correctamente", Snackbar.LENGTH_SHORT).show();
            navController.navigate(R.id.mobile_navigation);
        } else {
            Snackbar.make(view, "Ha ocurrido un error", Snackbar.LENGTH_SHORT).show();

        }
    }
}