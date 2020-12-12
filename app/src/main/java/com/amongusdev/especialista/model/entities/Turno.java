package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Turno implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;
    @SerializedName("duracion")
    @Expose
    private Integer duracion;
    @SerializedName("estado")
    @Expose
    private Boolean estado;
    @SerializedName("diaAgendaId")
    @Expose
    private DiaAgenda diaAgenda;

}
