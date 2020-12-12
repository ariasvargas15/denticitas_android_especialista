package com.amongusdev.especialista.model.apiservice.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CitaBody {
    @SerializedName("clienteCedula")
    @Expose
    private String clienteCedula;
    @SerializedName("servicioId")
    @Expose
    private int servicioId;
    @SerializedName("turnoId")
    @Expose
    private int turnoId;
    @SerializedName("createTime")
    @Expose
    private String createTime;
}
