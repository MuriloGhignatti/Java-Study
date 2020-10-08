import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Matriz{

    private int hashCode;

     private double[][] matriz;
     private int line;
     private int column;
     private String id;
     private Random rand = new Random();

    public Matriz(String id, int line, int column){
        this.matriz = new double[line][column];
        this.line = line;
        this.column = column;
        this.id = id;
        generateHashCode(); 
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public double[][] readFile(String file, String separator) {
        try (BufferedReader br = new BufferedReader(new FileReader("pathtocsvfile.csv"))) {
            String line;
            double[][] result = null;
            int counter = 0;
            while((line = br.readLine()) != null){
                if(result == null){
                    String[] size = line.split(separator);
                    result = new double[Integer.valueOf(size[0])][Integer.valueOf(size[1])];
                }
                else{
                    String[] numbers = line.split(separator);
                    for(int i = 0; i < numbers.length; i++){
                        result[counter][i] = Double.valueOf(numbers[i]);
                    }
                }
            }
            return result;
        }
        catch(IOException | NumberFormatException e){
            e.printStackTrace();
            return null;
        }
    }

    public void generateMatrix(String fileName) {
        try(FileWriter fileWriter = new FileWriter("./resources/" + hashCode + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
                bufferedWriter.write(this.line + "," + this.column + '\n');
            for (int l = 0; l < line; l++){
                for (int c = 0; c < column; c++){
                    matriz[l][c] = rand.nextDouble() * 1000;
                    if(c != column - 1)
                        bufferedWriter.write(String.valueOf(matriz[l][c]) + ",");
                    else
                        bufferedWriter.write(String.valueOf(matriz[l][c]));
                }
                bufferedWriter.write("\n");
            }
            Main.files.put(fileName, hashCode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void generateHashCode() {
        this.hashCode = 7;
        this.hashCode += 31 * id.hashCode();
        this.hashCode += 31 * this.line;
        this.hashCode += 31 * this.column;
    }
}
