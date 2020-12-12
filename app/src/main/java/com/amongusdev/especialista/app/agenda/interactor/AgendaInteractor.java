package com.amongusdev.especialista.app.agenda.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.agenda.interfaces.IAgenda;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.entities.Agenda;
import com.amongusdev.especialista.model.entities.Especialista;

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
                presenter.setAgendas(new ArrayList<>());
                Log.e("getAgendas", "Response failed");
            }
        });
    }
}
