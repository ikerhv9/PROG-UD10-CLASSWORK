package org.iker.Activitat2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Activitat2 {
    public static void main(String[] args) {
        System.out.println(leerContenidoFichero());
    }


    public static String leerContenidoFichero(){
        StringBuilder contenidoFichero = new StringBuilder();

        try {
            FileReader fileReader = new FileReader("resources"+ File.separatorChar + "a2" + File.separatorChar + "fichero.txt");
            int character;

            do {
                character = fileReader.read();
                if (character >= 0){
                    contenidoFichero.append((char) character);
                }else {
                    fileReader.close();
                }
            }while (character > 0);
        }catch (FileNotFoundException e){
            System.out.println("El fichero que quieres leer no existe");
        }catch (IOException e){
            System.out.println("Se ha produciod un error en el acceso al fichero");
        }
        return contenidoFichero.toString();
    }
}
