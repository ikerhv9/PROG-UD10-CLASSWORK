package org.iker.Activitat4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Activitat4 {
    public static void main(String[] args) {
        try {
            leerFicheroConBuffered();
        }catch (IOException e){
            e.getMessage();
        }

    }

    private static void leerFicheroConBuffered() throws IOException {
        BufferedReader bufferedReader = null;

        try {
            File file = new File("resources" + File.separatorChar + "a4" + File.separatorChar + "productos.txt");
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
