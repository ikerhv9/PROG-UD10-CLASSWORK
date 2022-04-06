package org.iker.Activitat8.exceptions;

public class ReservaNotFoundException extends Exception{
    public ReservaNotFoundException(String message) {
        super("La reserva con código " + message + " no ha sido encontrado");
    }
}
