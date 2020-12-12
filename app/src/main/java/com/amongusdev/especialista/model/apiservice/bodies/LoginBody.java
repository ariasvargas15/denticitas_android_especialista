package com.amongusdev.especialista.model.apiservice.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginBody implements Serializable {
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("tipo")
    @Expose
    private String tipo;

}
