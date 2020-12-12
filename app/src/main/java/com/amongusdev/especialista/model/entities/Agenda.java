package com.amongusdev.especialista.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Agenda implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mes")
    @Expose
    private Integer mes;
    @SerializedName("anio")
    @Expose
    private Integer anio;
    @SerializedName("diaAgendaList")
    @Expose
    private List<DiaAgenda> diaAgendaList;
    @SerializedName("especialistaCedula")
    @Expose
    private Especialista especialista;
}
