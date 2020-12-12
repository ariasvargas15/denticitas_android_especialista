package com.amongusdev.especialista.app.agenda.interactor;

import android.util.Log;

import com.amongusdev.especialista.app.agenda.interfaces.IRegistro;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.entities.Turno;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroInteractor implements IRegistro.Interactor, Callback<Void> {

    IRegistro.Presenter presenter;
    ArrayList<Turno> turnos;

    public RegistroInteractor(IRegistro.Presenter presenter){
        this.presenter = presenter;
        turnos = new ArrayList<>();
    }

    @Override
    public void registrarTurnos(String cedula, int anio, int mes, int dia, ArrayList<String> array) {

        Call<Void> call = ApiAdapter.getApiService().registrarTurnos(cedula, anio, mes+1, convertArray(dia, array).toString().replaceAll("\\[", "").replaceAll(" ", "").replaceAll("]", ""));
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
            this.presenter.success(true);

        } else {
            this.presenter.success(false);

            Log.e("cita", response.message() + "\n" + response.toString());
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Log.e("CitaError", call.toString());
        this.presenter.success(false);


    }

    public List<String> convertArray(int dia, ArrayList<String> arrayList){
        List<String> res = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++){
            res.add(diaToString(dia).concat(arrayList.get(i)).concat("30"));
        }
        return res;
    }

    public String diaToString(int dia){
        return dia < 10 ? "0" + dia : "" + dia;
    }
}
