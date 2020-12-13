package com.amongusdev.especialista.app.agenda.presenter;

import android.icu.util.ValueIterator;

import com.amongusdev.especialista.app.agenda.interactor.AgendaInteractor;
import com.amongusdev.especialista.app.agenda.interfaces.IAgenda;
import com.amongusdev.especialista.model.entities.Agenda;

import java.util.ArrayList;

public class AgendaPresenter implements IAgenda.Presenter {

    IAgenda.View view;
    IAgenda.Interactor interactor;

    public AgendaPresenter(IAgenda.View view) {
        this.view = view;
        this.interactor = new AgendaInteractor(this);
    }

    @Override
    public void getAgendas(String cedula) {
        interactor.getAgendas(cedula);
    }

    @Override
    public void setAgendas(ArrayList<Agenda> agendas) {
        view.setAgendas(agendas);
    }

    @Override
    public void showResponseDelete(boolean success) {
        view.showResponseDelete(success);
    }

    @Override
    public void deleteAgenda(String cedula) {
        interactor.deleteAgenda(cedula);
    }
}
