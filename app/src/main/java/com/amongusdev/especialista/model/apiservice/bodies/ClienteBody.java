package com.amongusdev.especialista.model.apiservice.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteBody implements Serializable {

    @SerializedName("ocupacion")
    @Expose
    private String ocupacion;
    @SerializedName("persona")
    @Expose
    private PersonaBody persona;

}
