import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Josek on 12/7/2017.
 */
public class Archivo {
    private File archivo;

    public void crearArchivo(String nombre, String extension) {
        if (!nombre.isEmpty() && !extension.isEmpty()) {
            try {
                this.archivo = new File(nombre + extension);
                this.archivo.createNewFile();
                //this.br = new BufferedReader(new FileReader(this.archivo));
            }
            catch (Exception ex) {
                System.out.println("Error al crear el archivo " + nombre + extension + " .");
            }
        }
        else {
            System.out.println("Los parámetros no pueden estar vacíos.");
        }
    }

    public void escribirEnArchivo(String linea, Boolean append) {
        linea += "\n";
        Charset.forName("UTF-8").encode(linea); //Sets the string to UTF-8.

        if(archivo != null) {
            try {
                FileWriter writer = new FileWriter(this.archivo, append);
                writer.write(linea);
                writer.flush();
                writer.close();
            }
            catch (Exception ex) {
                System.out.println("No se puede escribir \"" + linea + "\" en archivo.");
            }
        }
        else
            System.out.println("No se ha creado el archivo de salida.");
    }
}
