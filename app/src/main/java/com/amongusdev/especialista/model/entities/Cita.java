package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("clienteCedula")
    @Expose
    private Cliente cliente;
    @SerializedName("turnoId")
    @Expose
    private Turno turno;
    @SerializedName("servicioId")
    @Expose
    private Servicio servicio;

}