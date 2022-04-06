package org.iker.Activitat8.dao;

import org.iker.Activitat8.dto.Reserva;
import org.iker.Activitat8.exceptions.ReservaNotFoundException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileReservaDAO implements ReservaDAOInterface {

    private File file;

    private final String DATABASE_FILE = "resources/actividad8/reservas.txt";
    private final String FIELD_SEPARATOR = ";";

    private final int COL_CODIGO = 0;

    private final int COL_NOMBRE = 1;

    private final int COL_PLAZAS = 2;

    private final int COL_FECHA = 3;

    public FileReservaDAO() {
        this.file = new File(DATABASE_FILE);
    }

    public FileReservaDAO(String file) {
        this.file = new File(file);
    }

    // Devuelve todos las reservas del fichero
    @Override
    public ArrayList<Reserva> findAll() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            do {
                String reservaRegistro = bufferedReader.readLine();
                if (reservaRegistro == null) {
                    return reservas;
                }
                Reserva reserva = getReservaFromRegister(reservaRegistro);
                reservas.add(reserva);
            } while (true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // Busca la reserva cuyo codigo coincida con @id
    @Override
    public Reserva findById(String codReserva) {
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                do {
                    String reservaRegistro = bufferedReader.readLine();
                    if (reservaRegistro == null) {
                        return null;
                    }
                    Reserva reserva = getReservaFromRegister(reservaRegistro);
                    if (reserva.getCodigoReserva().equals(codReserva)) {
                        return reserva;
                    }
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    // Obtiene la reserva cuyo codigo coincida con @id
    @Override
    public Reserva getById(String codReserva) throws ReservaNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            do {
                String reservaRegistro = bufferedReader.readLine();
                if (reservaRegistro == null) {
                    throw new ReservaNotFoundException(codReserva);
                }
                Reserva reserva = getReservaFromRegister(reservaRegistro);
                if (reserva.getCodigoReserva().equals(codReserva)) {
                    return reserva;
                }
            } while (true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Reserva getReservaFromRegister(String reservaRegister) {
        String[] reservaField = reservaRegister.split(FIELD_SEPARATOR);
        String codReserva = reservaField[COL_CODIGO];
        String nombre = reservaField[COL_NOMBRE];
        int plazasReservadas = Integer.parseInt(reservaField[COL_PLAZAS]);
        LocalDateTime fechaRealizacion = LocalDateTime.parse(reservaField[COL_FECHA], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return new Reserva(codReserva, nombre, plazasReservadas, fechaRealizacion);
    }

    @Override
    public ArrayList<Reserva> findByUser(String userName) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            do {
                String reservaRegistro = bufferedReader.readLine();
                if (reservaRegistro == null) {
                    return reservas;
                }
                Reserva reserva = getReservaFromRegister(reservaRegistro);
                if (reserva.getUsuario().equals(userName)) {
                    reservas.add(reserva);
                }
            } while (true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Reserva> findByTravel(String codViaje) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            do {
                String reservaRegistro = bufferedReader.readLine();
                if (reservaRegistro == null) {
                    return reservas;
                }
                Reserva reserva = getReservaFromRegister(reservaRegistro);
                if (reserva.getCodigoReserva().equals(codViaje)) {
                    reservas.add(reserva);
                }
            } while (true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean save(Reserva reserva) {
        try {
            boolean actualizado = false;
            ArrayList<Reserva> reservas = findAll();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
                for (Reserva reservaSave: reservas) {
                    if (reserva.equals(reservaSave)){
                        bufferedWriter.write(getRegisterFromReserva(reserva));
                        bufferedWriter.newLine();
                        actualizado = true;
                    }
                }
                if (!actualizado){
                    bufferedWriter.write(getRegisterFromReserva(reserva));
                    bufferedWriter.newLine();
                    actualizado = true;
                }
                return actualizado;
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Reserva reserva) {
        try {
            boolean removed = false;
            ArrayList<Reserva> reservas = findAll();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
                for (Reserva reservaRemove : reservas) {
                    if (!reserva.equals(reservaRemove)) {
                        bufferedWriter.write(getRegisterFromReserva(reservaRemove));
                        bufferedWriter.newLine();
                    } else {
                        removed = true;
                    }
                }
            }
            return removed;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    private String getRegisterFromReserva(Reserva reserva) {
        return reserva.getCodigoReserva() + ";" + reserva.getUsuario() + ";" + reserva.getPlazasSolicitadas() + ";" + reserva.getFechaRealizacionFormatted();
    }

}
