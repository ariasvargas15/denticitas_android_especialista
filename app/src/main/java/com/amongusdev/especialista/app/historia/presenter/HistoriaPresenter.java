package com.amongusdev.especialista.app.historia.presenter;

import com.amongusdev.especialista.app.historia.interactor.HistoriaInteractor;
import com.amongusdev.especialista.app.historia.interfaces.IHistoria;
import com.amongusdev.especialista.model.entities.Evolucion;

import java.util.ArrayList;

public class HistoriaPresenter implements IHistoria.Presenter {

    IHistoria.View view;
    IHistoria.Interactor interactor;

    public HistoriaPresenter(IHistoria.View view) {
        this.view = view;
        this.interactor = new HistoriaInteractor(this);
    }

    @Override
    public void getHistoria(String cedula) {
        interactor.getHistoria(cedula);
    }

    @Override
    public void setHistoria(ArrayList<Evolucion> historia) {
        view.setHistoria(historia);
    }
}
