package com.amongusdev.especialista.app.citas.interfaces;

import com.amongusdev.especialista.model.entities.Cita;

import java.util.ArrayList;

public interface ICitas {
    interface View {
        void setCitas(ArrayList<Cita> citas);
    }
    interface Presenter{
        void getCitas(String cedula);
        void setCitas(ArrayList<Cita> citas);
    }
    interface Interactor{
        void getCitas(String cedula);
    }
}
