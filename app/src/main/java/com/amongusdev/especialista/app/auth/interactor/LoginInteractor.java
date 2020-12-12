package com.amongusdev.especialista.app.auth.interactor;

import android.content.Context;
import android.util.Log;

import com.amongusdev.especialista.app.auth.interfaces.ILogin;
import com.amongusdev.especialista.model.apiservice.ApiAdapter;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.apiservice.bodies.LoginBody;
import com.amongusdev.especialista.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements ILogin.Interactor, Callback<GenericResponse> {

    ILogin.Presenter presenter;
    String user;
    Context context;

    public LoginInteractor(ILogin.Presenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void validateData(String user, String password, String tipo) {
        this.user = user;
        LoginBody loginBody = new LoginBody(user, password, tipo);
        Call<GenericResponse> call = ApiAdapter.getApiService().login(loginBody);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
        if (response.isSuccessful()) {
            GenericResponse res = response.body();
            if (res != null){
                boolean r;
                r = res.getMessage().equals("success");
                if (r){
                    Utils.saveValuePreference(context, "auth", user);
                }
                this.presenter.sendResponse(r);
            }
        } else {
            Log.e("login", response.message() + "\n" + response.toString());
        }
    }

    @Override
    public void onFailure(Call<GenericResponse> call, Throwable t) {
        Log.e("LoginError", call.toString());
    }
}
