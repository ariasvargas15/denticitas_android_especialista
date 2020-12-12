package com.amongusdev.especialista.app.auth.presenter;

import android.content.Context;

import com.amongusdev.especialista.app.auth.interactor.LoginInteractor;
import com.amongusdev.especialista.app.auth.interfaces.ILogin;

public class LoginPresenter implements ILogin.Presenter {

    ILogin.Interactor interactor;
    ILogin.View view;

    public LoginPresenter(ILogin.View view, Context context){
        this.view = view;
        interactor = new LoginInteractor(this, context);
    }

    @Override
    public void validateData(String user, String password, String tipo) {
        interactor.validateData(user, password, tipo);
    }

    @Override
    public void sendResponse(boolean successful) {
        view.sendResponse(successful);
    }
}
