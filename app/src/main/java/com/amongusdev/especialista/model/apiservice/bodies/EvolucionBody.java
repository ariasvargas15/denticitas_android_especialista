package com.amongusdev.especialista.model.apiservice.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EvolucionBody {
    @SerializedName("fecha")
    @Expose
    private Date fecha;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("idCita")
    @Expose
    private Integer idCita;
}
