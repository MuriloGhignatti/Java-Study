import java.util.HashMap;

public class Main {


    public static HashMap<String, Integer> files = new HashMap<>();

    public static void main(String[] args) {
        Matriz matriz = new Matriz("Felipos", 5, 6);
        matriz.generateMatrix("matrizInicial");
    }
}