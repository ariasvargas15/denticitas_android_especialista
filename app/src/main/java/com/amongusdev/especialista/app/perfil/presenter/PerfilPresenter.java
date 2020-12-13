package com.amongusdev.especialista.app.perfil.presenter;

import com.amongusdev.especialista.app.perfil.interactor.PerfilInteractor;
import com.amongusdev.especialista.app.perfil.interfaces.IPerfil;
import com.amongusdev.especialista.model.entities.Especialista;

import java.util.Date;

public class PerfilPresenter implements IPerfil.Presenter {
    IPerfil.View view;
    IPerfil.Interactor interactor;

    public PerfilPresenter(IPerfil.View view) {
        this.view = view;
        this.interactor = new PerfilInteractor(this);
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email) {
        interactor.setDatosPersonales(cedula, nombre, apellido, fechaNacimiento, telefono, direccion, email);
    }

    @Override
    public void showResponse(boolean success) {
        view.showResponse(success);
    }

    @Override
    public void getEspecialista(String cedula) {
        interactor.getEspecialista(cedula);
    }

    @Override
    public void setEspecialista(Especialista especialista) {
        view.setEspecialista(especialista);
    }
}
