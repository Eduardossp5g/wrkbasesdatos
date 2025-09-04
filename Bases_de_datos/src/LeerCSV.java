import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerCSV {
    public static void main(String[] args) {

        String archivoCSV =  "codigos_postales_hmo.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {

            String line;
            String cpString;
            int contador = 0;
            int lastCP = -1;

            while ((line = br.readLine()) != null) {

                cpString = line.substring(0, 5);
                int currentCP = Integer.parseInt(cpString);

                if (lastCP == -1) {
                    // Primer registro
                    lastCP = currentCP;
                    contador = 1;
                } else if (lastCP == currentCP) {
                    // Si el codigo postal es igual al anterior, se aumenta el contador
                    contador++;
                } else {
                    // Si cambia el codigo postal, imprime y reinicia el contador
                    System.out.printf("Codigo postal: %d - Numero de asentamientos: %d%n", lastCP, contador);
                    lastCP = currentCP;
                    contador = 1;
                }
            }

            // Imprimir el ultimo codigo postal que se proceso
            if (lastCP != -1) {
                System.out.printf("Codigo postal: %d - Numero de asentamientos: %d%n", lastCP, contador);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}