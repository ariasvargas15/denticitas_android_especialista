package com.amongusdev.especialista.app.historia.interactor;

import android.util.Log;


import com.amongusdev.especialista.app.historia.interfaces.IHistoria;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.entities.Evolucion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoriaInteractor implements IHistoria.Interactor {

    IHistoria.Presenter presenter;
    ArrayList<Evolucion> evolucion;

    public HistoriaInteractor(IHistoria.Presenter presenter) {
        this.presenter = presenter;
        evolucion = new ArrayList<>();
    }

    @Override
    public void getHistoria(String cedula) {
        Call<List<Evolucion>> call = ApiAdapter.getApiService().getEvolucion(cedula);
        call.enqueue(new Callback<List<Evolucion>>() {
            @Override
            public void onResponse(Call<List<Evolucion>> call, Response<List<Evolucion>> response) {
                if (response.isSuccessful()) {
                    evolucion = (ArrayList<Evolucion>)response.body();
                    if(evolucion != null) {
                        presenter.setHistoria(evolucion);
                    } else {
                        Log.e("onResponseEvolucion", "Response is null");
                        presenter.setHistoria(evolucion);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Evolucion>> call, Throwable t) {
                presenter.setHistoria(evolucion);
            }
        });

    }
}
