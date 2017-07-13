import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        /** Parámetros */
        Integer cantidadDocumentos = 79909; //args[]
        String rutaVocabuario = "C://Users//Josek//Desktop//DatasetProcesado//Vocabulario//vocabularioOrdenado.txt"; //args[]
        String rutaFrecuencias = "C://Users//Josek//Desktop//DatasetProcesado//Frecuencias//"; //args[]

        IndiceInvertido indiceInvertido = new IndiceInvertido(cantidadDocumentos, rutaVocabuario, rutaFrecuencias);
        indiceInvertido.crearIndiceInvertido();

        System.out.println("FIN !!");
    }
}
