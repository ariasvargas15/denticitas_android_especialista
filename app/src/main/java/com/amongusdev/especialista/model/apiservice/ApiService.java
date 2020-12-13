package com.amongusdev.especialista.model.apiservice;

import com.amongusdev.especialista.model.apiservice.bodies.CitaBody;
import com.amongusdev.especialista.model.apiservice.bodies.ClienteBody;
import com.amongusdev.especialista.model.apiservice.bodies.EspecialistaBody;
import com.amongusdev.especialista.model.apiservice.bodies.EvolucionBody;
import com.amongusdev.especialista.model.apiservice.bodies.GenericResponse;
import com.amongusdev.especialista.model.apiservice.bodies.LoginBody;
import com.amongusdev.especialista.model.entities.Agenda;
import com.amongusdev.especialista.model.entities.Cita;
import com.amongusdev.especialista.model.entities.Especialista;
import com.amongusdev.especialista.model.entities.Evolucion;
import com.amongusdev.especialista.model.entities.Servicio;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/servicio")
    Call<List<Servicio>> getServicios();

    @GET("/cita/especialista/{cedula}")
    Call<List<Cita>> getCitasEspecialista(@Path("cedula") String cedula);

    @GET("/area/{id}/especialista")
    Call<List<Especialista>> getEspecialistas(@Path("id") int id);

    @GET("/especialista/{cedula}/agenda")
    Call<List<Agenda>> getAgendaEspecialista(@Path("cedula") String cedula);

    @POST("/login")
    Call<GenericResponse> login(@Body LoginBody loginBody);

    @POST("/registro")
    Call<GenericResponse> registro(@Body LoginBody loginBody);

    @POST("/cita")
    Call<GenericResponse> createCita(@Body CitaBody citaBody);

    @PATCH("/cliente/{cedula}")
    Call<GenericResponse> setDatosCliente(@Path("cedula") String cedula,
                                          @Body ClienteBody clienteBody);

    @POST("/especialista/{cedula}/agenda")
    Call<Void> registrarTurnos(@Path("cedula") String cedula,
                                    @Query("anio") int anio,
                                    @Query("mes") int mes,
                                    @Query("turnos") String turno);

    @PATCH("/especialista/{cedula}")
    Call<GenericResponse> setDatosEspecialista(@Path("cedula") String cedula, @Body EspecialistaBody especialistaBody);

    @GET("/especialista/{cedula}")
    Call<Especialista> getEspecialista(@Path("cedula") String cedula);

    @POST("historiaclinica/{cedula}/evolucion")
    Call<GenericResponse> setEvolucion(@Path("cedula") String cedula, @Body EvolucionBody evolucionBody);

    @GET("historiaclinica/{cedula}/evolucion")
    Call<List<Evolucion>> getEvolucion(@Path("cedula") String cedula);

    @DELETE("agenda/{cedula}")
    Call<GenericResponse> deleteAgenda(@Path("cedula") String cedula);



}
