package com.amongusdev.especialista.app.citas.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.citas.interfaces.ICitas;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.entities.Cita;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasInteractor implements ICitas.Interactor {

    ICitas.Presenter presenter;
    ArrayList<Cita> citas;

    public CitasInteractor(ICitas.Presenter presenter) {
        this.presenter = presenter;
        citas = new ArrayList<>();
    }

    @Override
    public void getCitas(String cedula) {
        Call<List<Cita>> call = ApiAdapter.getApiService().getCitasEspecialista(cedula);
        call.enqueue(new Callback<List<Cita>>() {
            @Override
            public void onResponse(Call<List<Cita>> call, Response<List<Cita>> response) {
                if (response.isSuccessful()) {
                    citas = (ArrayList<Cita>) response.body();
                    if (citas != null) {
                        presenter.setCitas(citas);
                    } else {
                        Log.e("onResponseCita", "Response is null");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Cita>> call, Throwable t) {
                Log.e("getcitas", t.getLocalizedMessage());
                Log.e("getCitasFailed", "Response failed");
            }
        });
        presenter.setCitas(citas);
    }



}
