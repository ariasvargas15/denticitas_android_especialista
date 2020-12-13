package com.amongusdev.especialista.app.citas.presenter;

import com.amongusdev.especialista.app.citas.interactor.EvolucionInteractor;
import com.amongusdev.especialista.app.citas.interfaces.IEvolucion;
import com.amongusdev.especialista.model.entities.Cita;

public class EvolucionPresenter implements IEvolucion.Presenter {

    IEvolucion.View view;
    IEvolucion.Interactor interactor;

    public EvolucionPresenter(IEvolucion.View view) {
        this.view = view;
        this.interactor = new EvolucionInteractor(this);
    }

    @Override
    public void setEvolucion(Cita cita, String descripcion) {
        interactor.setEvolucion(cita, descripcion);
    }

    @Override
    public void showResponse(boolean success) {
        view.showResponse(success);
    }
}
