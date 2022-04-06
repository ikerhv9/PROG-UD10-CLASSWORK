package org.iker.Activitat1;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public static void crearFichero(String rutaDirectorio, String nombreArchivo) throws IOException {

        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()){
            throw new IOException();
        }

        File fichero = new File(rutaDirectorio, nombreArchivo);
        fichero.createNewFile();
    }

    public static void verArchivosDirectorio(String rutaDirectorio){
        File directorio = new File(rutaDirectorio);

        for (File fichero:directorio.listFiles()) {
            System.out.println(fichero.getName());
        }
    }

    public static void verInformacionArchivo(String rutaDirectorio, String nombreArchivo){
        File file = new File(rutaDirectorio, nombreArchivo);

        System.out.println("Nombre: " + file.getName());

        System.out.println("Ruta absoluta: " + file.getAbsolutePath());

        System.out.println("Ruta relativa: " + file.getPath());

        System.out.println("¿Permiso de escritura? " + file.canWrite());

        System.out.println("¿Permiso de lectura? " + file.canRead());

        System.out.println("¿Es un directorio? " + file.isDirectory());

        System.out.println("¿Es un archivo? " + file.isFile());

    }

}

