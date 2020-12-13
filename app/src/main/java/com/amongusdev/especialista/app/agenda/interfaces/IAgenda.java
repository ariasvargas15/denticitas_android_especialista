package com.amongusdev.especialista.app.agenda.interfaces;

import com.amongusdev.especialista.model.entities.Agenda;

import java.util.ArrayList;

public interface IAgenda {
    interface View {

        void setAgendas(ArrayList<Agenda> agendas);

        void showResponseDelete(boolean success);

    }

    interface Presenter {

        void getAgendas(String cedula);

        void setAgendas(ArrayList<Agenda> agendas);

        void showResponseDelete(boolean success);

        void deleteAgenda(String cedula);

    }

    interface Interactor {

        void getAgendas(String cedula);

        void deleteAgenda(String cedula);

    }
}
