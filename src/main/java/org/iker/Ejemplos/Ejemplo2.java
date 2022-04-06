package org.iker.Ejemplos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ejemplo2 {
    private static void escribirFicheroConBuffered() throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File("Ex7fitxer1.txt");
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Alumno 1");
            bufferedWriter.newLine();
            bufferedWriter.write("Alumno 2");
        }catch (IOException e){
            System.out.println("error escribiendo en el archivo " + e.getMessage());
        }finally {
            bufferedWriter.close();
        }
    }
}
