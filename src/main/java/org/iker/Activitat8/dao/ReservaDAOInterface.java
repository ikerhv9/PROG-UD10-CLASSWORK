package org.iker.Activitat8.dao;

import org.iker.Activitat8.dto.Reserva;
import org.iker.Activitat8.exceptions.ReservaNotFoundException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReservaDAOInterface {

    ArrayList<Reserva> findAll();
    Reserva findById(String codigo);
    Reserva getById(String codigo) throws ReservaNotFoundException, FileNotFoundException;
    ArrayList<Reserva> findByUser(String userName);
    ArrayList<Reserva> findByTravel(String codViaje);
    boolean save(Reserva reserva);
    boolean remove(Reserva reserva);
}
