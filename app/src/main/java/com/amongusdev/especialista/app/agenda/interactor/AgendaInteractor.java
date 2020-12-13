package com.amongusdev.especialista.app.agenda.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.agenda.interfaces.IAgenda;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.entities.Agenda;
import com.amongusdev.especialista.model.entities.Especialista;
import com.amongusdev.especialista.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaInteractor implements IAgenda.Interactor {

    IAgenda.Presenter presenter;
    ArrayList<Especialista> especialistas;
    ArrayList<Agenda> agendas;


    public AgendaInteractor(IAgenda.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAgendas(String cedula) {
        Call<List<Agenda>> call = ApiAdapter.getApiService().getAgendaEspecialista(cedula);
        call.enqueue(new Callback<List<Agenda>>() {
            @Override
            public void onResponse(Call<List<Agenda>> call, Response<List<Agenda>> response) {
                if (response.isSuccessful()) {
                    agendas = (ArrayList<Agenda>) response.body();
                    if (agendas != null) {
                        presenter.setAgendas(agendas);
                    } else {
                        Log.e("getAgendas", "Response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Agenda>> call, Throwable t) {
                Log.e("problema", t.getLocalizedMessage());
                presenter.setAgendas(new ArrayList<>());
                Log.e("getAgendas", "Response failed");
            }
        });
    }

    @Override
    public void deleteAgenda(String cedula) {
        Call<GenericResponse> call = ApiAdapter.getApiService().deleteAgenda(cedula);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    GenericResponse res = response.body();
                    if (res != null){
                        boolean r;
                        r = res.getMessage().equals("success");
                        presenter.showResponseDelete(r);
                    }
                } else {
                    Log.e("delete agenda", response.message() + "\n" + response.toString());
                    presenter.showResponseDelete(false);
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.e("Delete agenda", call.toString());
            }
        });
    }
}
