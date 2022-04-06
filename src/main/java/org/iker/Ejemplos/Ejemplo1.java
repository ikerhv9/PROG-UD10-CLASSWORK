package org.iker.Ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejemplo1 {
    private static void leerFicheroConBuffered() throws IOException {
        BufferedReader bufferedReader = null;

        try {
            File file = new File("EX5fitxer.txt");
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            do {
                String line = bufferedReader.readLine();
                if (line == null){
                    return;
                }
                System.out.println(line);
            }while (true);
        }catch (IOException e){
            System.out.println("Se ha producido durante la lectura del archivo " + e.getMessage());
        } finally {
            bufferedReader.close();
        }
    }
}
