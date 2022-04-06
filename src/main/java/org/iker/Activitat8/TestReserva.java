package org.iker.Activitat8;

import org.iker.Activitat8.dao.FileReservaDAO;
import org.iker.Activitat8.dto.Reserva;
import org.iker.Activitat8.exceptions.ReservaNotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestReserva {

    private FileReservaDAO fileReservaDAO;

    private static final String[] FILE_INITIAL_REGISTERS = {
            "1-1;antonio;4;2022-04-03 23:56:06",
            "1-2;alex;3;2022-01-12 12:12:22",
            "2-1;roberto;5;2022-04-03 23:56:06",
            "2-2;roberto;5;2022-04-03 23:56:06"
    };

    private static final String PATH_TO_TEST_FILE = "resources/actividad8/test/reservas.txt";

    private static void prepareTestDBFile() {
        try {
            File file = new File(PATH_TO_TEST_FILE);
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(file))) {
                for (String registerReserva: FILE_INITIAL_REGISTERS) {
                    bufferedWriter.write(registerReserva);
                    bufferedWriter.newLine();
                }
            }
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        prepareTestDBFile();
        TestReserva testReserva = new TestReserva();
        testReserva.testFindById();
        testReserva.testGetById();
        testReserva.testFindAll();
        testReserva.testFindByUser();
        testReserva.testFindByTravel();
        testReserva.testRemove();
        testReserva.testSave();
    }

    public TestReserva() {
        this.fileReservaDAO = new FileReservaDAO(PATH_TO_TEST_FILE);
    }

    private void testFindById() {
        testFindById("1-2");
        testFindById("2-1");

    }

    private void testFindById(String codTest) {
        Reserva reserva = this.fileReservaDAO.findById(codTest);

        if (reserva == null) {
            System.out.println("La reserva con codigo " + codTest + " no ha sido encontrada");
        } else if (!reserva.getCodigoReserva().equals(codTest)) {
            System.out.println("Codigo esperado " + codTest + " codigo obtenido " + reserva.getCodigoReserva());
        } else {
            System.out.println("OK");
        }
    }

    private void testGetById() {
        testGetById("1-2");
        testGetById("2-1");
    }

    private void testGetById(String codTest) {
        try {
            Reserva reserva = this.fileReservaDAO.getById(codTest);
            if (reserva == null) {
                System.out.println("La reserva con código "
                        + codTest + "no ha sido encontrada");
            } else if (!reserva.getCodigoReserva().equals(codTest)) {
                System.out.println("Código esperado:" + codTest
                        + " Código obtenido: "+ reserva.getCodigoReserva());
            } else {
                System.out.println("OK");
            }
        }catch (ReservaNotFoundException ex) {
            System.out.println("Reserva " + codTest + " no encontrada");
        }
    }


    private void testFindAll() {
        ArrayList<Reserva> reservas = this.fileReservaDAO.findAll();

        int numReservasEperadas = 3;
        if (reservas.size() != numReservasEperadas) {
            System.out.println("Esperaba " + numReservasEperadas + " reservas");
        } else if (!reservas.get(0).getCodigoReserva().equals("1-1")) {
            System.out.println("Esperaba primera reserva 1-1");
        } else {
            System.out.println("OK");
        }
    }

    private void testFindByUser() {
        testFindByUser("juan");
        testFindByUser("Pedro");
        testFindByUser("pepe");
    }

    private void testFindByUser(String nombre) {
        ArrayList<Reserva> reservas = this.fileReservaDAO.findByUser(nombre);

        if (reservas.size() == 0) {
            System.out.println("No hay reservas con ese nombre");
        } else if (!reservas.get(0).getUsuario().equals(nombre)) {
            System.out.println("Esperaba " + nombre);
        } else {
            System.out.println("OK");
        }
    }


    private void testFindByTravel() {
        testFindByTravel("1-1");
        testFindByTravel("2-1");
        testFindByTravel("1-3");
    }


    private void testFindByTravel(String codViaje) {
        ArrayList<Reserva> reservas = this.fileReservaDAO.findByTravel(codViaje);

        if (reservas.size() == 0) {
            System.out.println("Código incorrecto");
        } else if (!reservas.get(0).getCodigoReserva().equals(codViaje)) {
            System.out.println("Esperaba " + codViaje);
        } else {
            System.out.println("OK");
        }
    }


    private void testRemove() {
        String codReserva = "1-2";
        Reserva reserva = this.fileReservaDAO.findById(codReserva);
        if (reserva == null) {
            System.out.println("La reserva " + codReserva + " no existe");
        } else {
            boolean eliminado = this.fileReservaDAO.remove(reserva);
            if (!eliminado){
                System.out.println("La reserva " + codReserva + " no se ha podido eliminar");
            }else {
                reserva = this.fileReservaDAO.findById(codReserva);
                if (reserva != null){
                    System.out.println("La reserva " + codReserva + " no se ha podido eliminar");
                }else {
                    System.out.println("La reserva " + codReserva + " ha sido eliminada");
                }
            }
        }
    }

    private void testSave(){
        String codReserva = "1-1";
        Reserva reserva = this.fileReservaDAO.findById(codReserva);
        if (reserva == null){
            System.out.println("La reserva " + codReserva + " no existe");
        } else {
            boolean saved = this.fileReservaDAO.save(reserva);
            if (!saved){
                System.out.println("La reserva " + codReserva + " no se ha podido actualizar");
            }else {
                if (!reserva.equals(this.fileReservaDAO.findById(codReserva))){
                    System.out.println("La reserva " + codReserva + " no se ha podido actualizar");
                }else {
                    System.out.println("La reserva " + codReserva + " se ha podido actualizar");
                }
            }
        }
    }
}
