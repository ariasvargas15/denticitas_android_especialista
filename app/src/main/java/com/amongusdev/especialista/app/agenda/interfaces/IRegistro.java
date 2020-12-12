package com.amongusdev.especialista.app.agenda.interfaces;

import java.util.ArrayList;

public interface IRegistro {
    interface View {
        void success(boolean success);
    }
    interface Presenter{
        void success(boolean success);
        void registrarTurnos(String cedula, int anio, int mes, int dia, ArrayList<String> turnos);
    }
    interface Interactor{
        void registrarTurnos(String cedula, int anio, int mes, int dia, ArrayList<String> turnos);
    }
}
