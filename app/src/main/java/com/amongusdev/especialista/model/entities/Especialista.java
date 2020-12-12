package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Especialista implements Serializable {
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("areaEspecializacionList")
    @Expose
    private List<Area> areaList;
    @SerializedName("persona")
    @Expose
    private Persona persona;
    @SerializedName("agendaList")
    @Expose
    private List<Agenda> agendaList;
}
