package org.iker.Activitat8.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {

    private String codigoReserva;

    private String usuario;

    private int plazasSolicitadas;

    private LocalDateTime fechaRealizacion;

    public Reserva(String codigoReserva, String usuario, int plazasSolicitadas) {
        this.codigoReserva = codigoReserva;
        this.usuario = usuario;
        this.plazasSolicitadas = plazasSolicitadas;
        this.fechaRealizacion = LocalDateTime.now();
    }

    public Reserva(String codigoReserva, String usuario, int plazasSolicitadas, LocalDateTime fechaRealizacion) {
        this.codigoReserva = codigoReserva;
        this.usuario = usuario;
        this.plazasSolicitadas = plazasSolicitadas;
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public boolean perteneceAlViaje(String codViaje) {
        return codigoReserva.split("-")[0].equals(codViaje);
    }

    public int getPlazasSolicitadas() {
        return plazasSolicitadas;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaRealizacionFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(this.fechaRealizacion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(codigoReserva, reserva.codigoReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoReserva);
    }
}
