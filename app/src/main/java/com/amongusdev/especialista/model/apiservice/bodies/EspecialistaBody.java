package com.amongusdev.especialista.model.apiservice.bodies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EspecialistaBody {
    @SerializedName("persona")
    @Expose
    private PersonaBody persona;
}
