package com.amongusdev.especialista.app.citas.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.citas.interfaces.IEvolucion;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.apiservice.bodies.EvolucionBody;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.entities.Cita;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EvolucionInteractor implements IEvolucion.Interactor {

    IEvolucion.Presenter presenter;

    public EvolucionInteractor(IEvolucion.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setEvolucion(Cita cita, String descripcion) {
        EvolucionBody evolucion = new EvolucionBody(Calendar.getInstance().getTime(), descripcion, null, cita.getId());
        Call<GenericResponse> call = ApiAdapter.getApiService().setEvolucion(cita.getCliente().getCedula(), evolucion);
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
                    Log.e("evolucion", response.message() + "\n" + response.toString());
                    presenter.showResponse(false);
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                Log.e("Evolucion", call.toString());
                presenter.showResponse(false);
            }
        });

    }
}
