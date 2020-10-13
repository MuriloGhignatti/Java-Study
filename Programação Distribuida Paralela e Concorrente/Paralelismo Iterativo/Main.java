import java.util.HashMap;

public class Main {


    public static HashMap<String, Integer> files = new HashMap<>();
    private static final int numberOfCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Nucleos Disponiveis: " + numberOfCores);

        new Matriz("0", 1000, 1000).generateMatrix("matriz0", ",");
        new Matriz("1", 2000, 2000).generateMatrix("matriz1", ",");
        new Matriz("2", 1000, 2000).generateMatrix("matriz2", ",");
        new Matriz("3", 2000, 4000).generateMatrix("matriz3", ",");

        //double[][] m1 = {{0,1,2}, {3,4,5}, {6,7,8}};
        //double[][] m2 = {{6,7,8}, {3,4,5}, {0,1,2}};
        //Double[][] resultado = new Double[3][3];

        double[][] m1 = Matriz.readFile(files.get("matriz0") + ".txt", ",");
        double[][] m2 = Matriz.readFile(files.get("matriz2") + ".txt", ",");
        Double[][] resultado = new Double[m1[0].length][m2.length];

        Calculadora[] calculadoras = new Calculadora[numberOfCores];

        int inicio = 1;
        for(int i = 0; i < numberOfCores; i++){
            int linesPerCore = m1[0].length/numberOfCores;
            calculadoras[i] = new Calculadora(inicio, inicio + linesPerCore, m1, m2, resultado);
        }

        System.out.println(resultado.toString());
    }
}