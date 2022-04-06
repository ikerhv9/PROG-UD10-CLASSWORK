package org.iker.Activitat1;

import java.io.IOException;

public class TestFileManager {
    public static void main(String[] args) {
        try {
            FileManager.crearFichero("resources", "prueba1.txt");
        }catch (IOException e){
            System.out.println("Error. No se pudo crear el fichero.");
        }
        FileManager.verArchivosDirectorio("resources");

        FileManager.verInformacionArchivo("resources","prueba1.txt");
    }
}
