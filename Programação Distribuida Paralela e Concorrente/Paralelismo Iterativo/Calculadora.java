public class Calculadora extends Thread{
    
    private int lineStart;
    private int lineEnd;
    private double[][] matrixOne;
    private double[][] matrixTwo;
    private Double[][] result;

    public Calculadora(int lineStart, int lineEnd, double[][] matrixOne, double[][] matrixTwo, Double[][] result){
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
        this.matrixOne = matrixOne;
        this.matrixTwo = matrixTwo;
        this.result = result;
    }

    public void calculateLine(){
        for(int lines = (lineStart - 1); lines < lineEnd; lines++){
            int counter = 0;
            for(int l = 0; l < matrixOne.length; l++){
                double tempResult = 0;
                for(int c = 0; c < matrixOne[l].length; c++){
                    tempResult += matrixOne[lines][c] * matrixTwo[c][counter];
                }
                result[lines][counter] = tempResult;
                counter++;
            }
        }
    }

    @Override
    public void run() {
        calculateLine();
    }
}
