import java.io.*;

import static java.lang.Math.log;

/**
 * Created by Josek on 12/7/2017.
 */
public class IndiceInvertido {
    Archivo archivoIndice;
    Archivo archivoPostings;
    Integer cantidadDocumentos;
    Integer posicionActual;
    String rutaVocabulario;
    String rutaFrecuencias;

    IndiceInvertido(Integer cantidadDocumentos, String rutaVocabulario, String rutaFrecuencias){
        this.archivoIndice = new Archivo();
        this.archivoPostings = new Archivo();
        this.archivoIndice.crearArchivo("indiceInvertido", ".txt");
        this.archivoPostings.crearArchivo("postings", ".txt");
        this.cantidadDocumentos = cantidadDocumentos;
        this.posicionActual = 0;
        this.rutaVocabulario = rutaVocabulario;
        this.rutaFrecuencias = rutaFrecuencias;
    }

    public void crearIndiceInvertido() throws IOException {
        /** Carga el archivo de vocabulario */
        BufferedReader archivoVocabulario = new BufferedReader(new InputStreamReader(new FileInputStream(this.rutaVocabulario), "utf-8"));
        String lineaVocabularioActual = archivoVocabulario.readLine();

        while(lineaVocabularioActual != null){
            String[] entradaVocabulario = lineaVocabularioActual.split("\\s");
            this.archivoIndice.escribirEnArchivo(entradaVocabulario[0] + " " + this.posicionActual + " " + entradaVocabulario[1], true);
            this.posicionActual += Integer.parseInt(entradaVocabulario[1]);

            System.out.println("Palabra: " + entradaVocabulario[0] + " agregada al índice\n");

            Integer archivosEncontrados = 0;
            Integer archivoFrecuenciaActual = 0;
            while(archivoFrecuenciaActual < this.cantidadDocumentos){
                System.out.println("Archivo " + archivoFrecuenciaActual + "\n");
                if(archivosEncontrados < Integer.parseInt(entradaVocabulario[1])){
                    /** Carga el archivo de frecuencia */
                    BufferedReader archivoFrecuencia = new BufferedReader(new InputStreamReader(new FileInputStream(this.rutaFrecuencias + "/" + archivoFrecuenciaActual + ".freq"), "utf-8"));
                    String lineaFrecuenciaActual = archivoFrecuencia.readLine();

                    while(lineaFrecuenciaActual != null){
                        String[] entradaFrecuencia = lineaFrecuenciaActual.split("\\s");

                        if(entradaFrecuencia[0].equals(entradaVocabulario[0])){
                            Double tf = 1 + log(Double.parseDouble(entradaFrecuencia[1]));
                            Double idf = log(1 + (cantidadDocumentos/Integer.parseInt(entradaVocabulario[1])));
                            Double peso = tf*idf;

                            this.archivoPostings.escribirEnArchivo(archivoFrecuenciaActual + " " + peso, true);
                            archivosEncontrados++;
                            System.out.println("posting agregado\n");
                            break;
                        }

                        lineaFrecuenciaActual = archivoFrecuencia.readLine();
                    }

                    archivoFrecuencia.close();
                }
                else{
                    System.out.println("no más postings\n");
                    break;
                }
                archivoFrecuenciaActual++;
            }
            lineaVocabularioActual = archivoVocabulario.readLine();
        }

        archivoVocabulario.close();
    }
}