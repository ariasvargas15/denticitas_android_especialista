package com.amongusdev.especialista.app.perfil.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.perfil.interfaces.IPerfil;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.apiservice.bodies.EspecialistaBody;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.apiservice.bodies.PersonaBody;
import com.amongusdev.especialista.model.entities.Especialista;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilInteractor implements IPerfil.Interactor {

    IPerfil.Presenter presenter;

    public PerfilInteractor(IPerfil.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setDatosPersonales(String cedula, String nombre, String apellido, Date fechaNacimiento, String telefono, String direccion, String email) {
        PersonaBody personaBody = new PersonaBody(nombre, apellido, fechaNacimiento, telefono, direccion, email, cedula, null);
        EspecialistaBody especialistaBody= new EspecialistaBody(personaBody);
        Call<GenericResponse> call = ApiAdapter.getApiService().setDatosEspecialista(cedula, especialistaBody);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    GenericResponse res = response.body();
                    if (res != null) {
                        boolean r;
                        r = res.getMessage().equals("success");
                        presenter.showResponse(r);
                    }
                } else {
                    Log.e("registro", response.message() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.e("CompletarDatosError", call.toString());
            }
        });
    }

    @Override
    public void getEspecialista(String cedula) {
        Call<Especialista> call = ApiAdapter.getApiService().getEspecialista(cedula);
        call.enqueue(new Callback<Especialista>() {
            @Override
            public void onResponse(Call<Especialista> call, Response<Especialista> response) {
                if (response.isSuccessful()) {
                    Especialista c = response.body();
                    if (c != null) {
                        presenter.setEspecialista(c);
                    }
                } else {
                    Log.e("getCliente", response.message() + "\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<Especialista> call, Throwable t) {
                Log.e("GetEspeciaalista", call.toString());
            }
        });
    }
}
