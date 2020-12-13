package com.amongusdev.especialista.app.perfil.interfaces;

import com.amongusdev.especialista.model.entities.Especialista;

import java.util.Date;

public interface IPerfil {
    interface View {
        void showResponse(boolean success);
        void setEspecialista(Especialista especialista);
    }

    interface Presenter {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email);
        void showResponse(boolean success);
        void getEspecialista(String cedula);
        void setEspecialista(Especialista especialista);
    }

    interface Interactor {
        void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento,
                                String telefono, String direccion, String email);
        void getEspecialista(String cedula);
    }
}
