package com.amongusdev.especialista.app.agenda.presenter;

import com.amongusdev.especialista.app.agenda.interactor.RegistroInteractor;
import com.amongusdev.especialista.app.agenda.interfaces.IRegistro;

import java.util.ArrayList;

public class RegistroPresenter implements IRegistro.Presenter {

    IRegistro.Interactor interactor;
    IRegistro.View view;

    public RegistroPresenter(IRegistro.View view){
        this.view = view;
        interactor = new RegistroInteractor(this);
    }

    @Override
    public void success(boolean success) {
        view.success(success);
    }

    @Override
    public void registrarTurnos(String cedula, int anio, int mes, int dia, ArrayList<String> turnos) {
        interactor.registrarTurnos(cedula, anio, mes, dia, turnos);
    }


}
