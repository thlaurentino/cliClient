import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class RandomApiClient {

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("Pressione pra iniciar...");

        Scanner sc = new Scanner(System.in); char ch = sc.next().charAt(0);

        try {
            for (int i = 1; i <= 100000; i++) {
                // Generate two random integer numbers
                int num1 = random.nextInt(100);
                int num2 = random.nextInt(100);

                System.out.println("Iteração: " + i);
                System.out.println("Numero aleatório 1: " + num1);
                System.out.println("Numero aleatório 2: " + num2);

                // execute operacao de soma
                int sum = performOperation("http://192.168.1.198:55001/add?num1=" + num1 + "&num2=" + num2);
                System.out.println("Soma: " + sum);

             //  int multiply = performOperation("http://localhost:8000/multiply?num1=" + num1 + "&num2=" + num2);
               // System.out.println("Mutiplicação: " + multiply);

                // execute operacao de subtração
                //int difference = performOperation("http://localhost:5050/sub?num1=" + num1 + "&num2=" + num2);
                //System.out.println("Subtração: " + difference);
                //System.out.println("----------------------");
            }

        } catch (IOException e) {
            System.err.println("Erro lendo a entrada: " + e.getMessage());
        }
    }

    private static int performOperation(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.readLine();
        reader.close();
        connection.disconnect();

        return Integer.parseInt(response);
    }
}
