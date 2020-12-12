package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente implements Serializable {

    @SerializedName("ocupacion")
    @Expose
    private String ocupacion;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("persona")
    @Expose
    private Persona persona;
    @SerializedName("citaList")
    @Expose
    private List<Cita> citaList;
}
