package org.iker.Activitat5;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Activitat5 {
    public static void main(String[] args) {
        try {
            escribirFicheroConBuffered(leerFicheroConBuffered());
        }catch (IOException e){
            e.getMessage();
        }
    }

    private static void escribirFicheroConBuffered(ArrayList<String> modulos) throws IOException {
        BufferedWriter bufferedWriter;
        try {
            File file = new File("resources" + File.separatorChar + "a5" + File.separatorChar + "modulosMayusculas.txt");
            try (FileWriter fileWriter = new FileWriter(file)) {
                bufferedWriter = new BufferedWriter(fileWriter);
                for (String modulo:modulos) {
                    String modulosEnMayuscula = modulo.toUpperCase(Locale.ROOT);
                    bufferedWriter.write(modulosEnMayuscula);
                    bufferedWriter.write("\n");
                }
                bufferedWriter.close();
            }
        }catch (IOException e){
            System.out.println("error escribiendo en el archivo " + e.getMessage());
        }
    }

    private static ArrayList<String> leerFicheroConBuffered() throws IOException {
        BufferedReader bufferedReader;
        ArrayList<String> lineas = new ArrayList<>();
        try {
            File file = new File("resources" + File.separatorChar + "a5" + File.separatorChar + "modulos.txt");
            try (FileReader fileReader = new FileReader(file)) {
                bufferedReader = new BufferedReader(fileReader);
            }
            do {
                String line = bufferedReader.readLine();
                if (line == null){
                    bufferedReader.close();
                    return lineas;
                }
                lineas.add(line);
            }while (true);
        }catch (IOException e){
            System.out.println("Se ha producido durante la lectura del archivo " + e.getMessage());
        }
        return null;
    }
}
