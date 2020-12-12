package com.amongusdev.especialista.app.auth.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.amongusdev.especialista.DashboardActivity;
import com.amongusdev.especialista.R;
import com.amongusdev.especialista.app.auth.interfaces.ILogin;
import com.amongusdev.especialista.app.auth.presenter.LoginPresenter;
import com.amongusdev.especialista.utils.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements ILogin.View {

    @BindView(R.id.input_cedula_login)
    TextInputEditText cedula;
    @BindView(R.id.input_pwd_login)
    TextInputEditText password;
    @BindView(R.id.login)
    LinearLayout login;
    private AlertDialog dialog;
    ILogin.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        presenter = new LoginPresenter(this, getApplicationContext());

        boolean res = Utils.getValuePreference(getApplicationContext(), "auth").equals("");

        if (!res){
            Utils.goToNextActivityCleanStack(this, DashboardActivity.class, true, null);
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        if (cedula.getText()!=null && password.getText()!=null && !cedula.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            SpotsDialog.Builder sp = new SpotsDialog.Builder();
            sp.setContext(LoginActivity.this).setCancelable(false).setMessage("Loading...");
            dialog = sp.build();
            dialog.show();
            presenter.validateData(cedula.getText().toString(), password.getText().toString(), "especialista");
        } else {
            Snackbar.make(login, "Digite los datos correctamente", Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void sendResponse(boolean successful) {
        dialog.dismiss();
        if (successful){
            Utils.goToNextActivityCleanStack(this, DashboardActivity.class, true, null);
        } else {
            Snackbar.make(login, "Datos incorrectos", Snackbar.LENGTH_SHORT).show();
        }

    }
}