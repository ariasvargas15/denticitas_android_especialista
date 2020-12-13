package com.amongusdev.especialista.app.historia.interfaces;


import com.amongusdev.especialista.model.entities.Evolucion;

import java.util.ArrayList;

public interface IHistoria {
    interface View {
        void setHistoria(ArrayList<Evolucion> historia);
    }
    interface Presenter{
        void getHistoria(String cedula);
        void setHistoria(ArrayList<Evolucion> historia);

    }
    interface Interactor{
        void getHistoria(String cedula);
    }
}
