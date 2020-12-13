package com.amongusdev.especialista.app.citas.interfaces;

import com.amongusdev.especialista.model.entities.Cita;

import java.util.ArrayList;

public interface IEvolucion {
    interface View {
        void showResponse(boolean success);
    }
    interface Presenter{
        void setEvolucion(Cita cita, String descripcion);
        void showResponse(boolean success);

    }
    interface Interactor{
        void setEvolucion(Cita cita, String descripcion);
    }
}
