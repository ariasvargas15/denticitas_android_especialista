package com.amongusdev.especialista.app.agenda.presenter;

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
}
