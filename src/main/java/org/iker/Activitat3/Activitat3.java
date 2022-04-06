package org.iker.Activitat3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Activitat3 {
    public static void main(String[] args) {
        escribirFichero("Iker Horcajo Vélez");

    }

    private static void escribirFichero(String cadena) {
        File file = new File("resources" + File.separatorChar + "a3" + File.separatorChar + "fichero.txt");
         try {
             file.createNewFile();
             FileWriter fileWriter = new FileWriter(file);
             fileWriter.write(cadena);
             fileWriter.close();
         } catch (IOException e){
             e.printStackTrace();
         }
    }
}
