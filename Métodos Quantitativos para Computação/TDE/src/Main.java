import java.util.Random;

public class Main {
    public static void main(String[] args) {
        simula(6, 4, 10500);
    }

    public static void simula(int n, int k){
        int nSim = 1000;
        System.out.println("Iniciando Calculo do Passeio Aleatório Teorico com: " + '\n' + "n: " + n + '\n' + "k: " + k);
        System.out.println("Resultado: " + passeioAleatorioT(n, k));
        System.out.println("Iniciando Calculo do Passeio Aleatório Simulado com: " + '\n' + "n: " + n + '\n' + "k: " + k + '\n' + "nSim: " + nSim);
        System.out.println("Resultado: " + passeioAleatorioS(n, k, nSim));
    }

    public static void simula(int n, int k, int nSim){
        System.out.println("Iniciando Calculo do Passeio Aleatório Teorico com: " + '\n' + "n: " + n + '\n' + "k: " + k);
        System.out.println("Resultado: " + passeioAleatorioT(n, k));
        System.out.println("Iniciando Calculo do Passeio Aleatório Simulado com: " + '\n' + "n: " + n + '\n' + "k: " + k + '\n' + "nSim: " + nSim);
        System.out.println("Resultado: " + passeioAleatorioS(n, k, nSim));
    }

    public static double passeioAleatorioT(int n, int k){
        double pTeorica = 0;
        if((n % 2 == 0 && k % 2 == 0) || (n % 2 != 0 && k % 2 != 0)){
            pTeorica = combinacao(n, (n + k)/2)/Math.pow(2, n);
        }
        return pTeorica;
    }

    public static float passeioAleatorioS(int n, int k, int nSim){
        Random random = new Random();

        int[] vetorRandom = new int[n];

        if((n % 2 == 0 && k % 2 == 0) || (n % 2 != 0 && k % 2 != 0)){
            float hit = 0.0F;
            for(int i = 0; i < nSim; i++){
                for(int tamanhoVetor = 0; tamanhoVetor < n; tamanhoVetor++){
                    vetorRandom[tamanhoVetor] = random.nextInt(2) + 1;
                }
                int pos = 0;
                for(int j = 0; j < n; j++){
                    if(vetorRandom[j] == 1)
                        pos++;
                    else
                        pos--;
                }
                if(pos == k)
                    hit++;
            }
            return hit/nSim;
        }
        return -1.0F;
    }

    public static double fatorial(double n){
        if(n == 0.0F || n == 1.0F)
            return 1;
        else
            return n * fatorial(--n);
    }

    public static double combinacao(double n, double p){
        return (fatorial(n) / ((fatorial(n - p)) * fatorial(p)));
    }
}
