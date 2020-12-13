package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Evolucion {

    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("fecha")
    @Expose
    private Date fecha;
    @SerializedName("idCita")
    @Expose
    private Integer idCita;
    @SerializedName("idHistoria")
    @Expose
    private Integer idHistoria;
    @SerializedName("imagen")
    @Expose
    private String imagen;
}

