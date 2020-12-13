package com.amongusdev.especialista.app.citas.presenter;


import com.amongusdev.especialista.app.citas.interactor.CitasInteractor;
import com.amongusdev.especialista.app.citas.interfaces.ICitas;
import com.amongusdev.especialista.model.entities.Cita;

import java.util.ArrayList;

public class CitasPresenter implements ICitas.Presenter {

    ICitas.Interactor interactor;
    ICitas.View view;

    public CitasPresenter(ICitas.View view) {
        this.view = view;
        interactor = new CitasInteractor(this);
    }

    @Override
    public void getCitas(String cedula) {
        interactor.getCitas(cedula);
    }

    @Override
    public void setCitas(ArrayList<Cita> citas) {
        view.setCitas(citas);
    }
}
