package com.amongusdev.especialista.app.agenda.interfaces;

import com.amongusdev.especialista.model.entities.Agenda;

import java.util.ArrayList;

public interface IAgenda {
    interface View {

        void setAgendas(ArrayList<Agenda> agendas);

    }

    interface Presenter {

        void getAgendas(String cedula);

        void setAgendas(ArrayList<Agenda> agendas);

    }

    interface Interactor {

        void getAgendas(String cedula);

    }
}
