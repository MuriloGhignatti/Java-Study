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

    public static double[][] readFile(String file, String separator) {
        try (BufferedReader br = new BufferedReader(new FileReader("./resources/" + file))) {
            String line;
            double[][] result = null;
            int counter = 0;
            int lines = -1;
            int columns = -1;
            while((line = br.readLine()) != null && counter != lines){
                if(result == null){
                    String[] size = line.split(separator);
                    lines = Integer.valueOf(size[0]);
                    columns = Integer.valueOf(size[1]);
                    result = new double[lines][columns];
                }
                else{
                    String[] numbers = line.split(separator);
                    for(int i = 0; i < numbers.length; i++){
                        result[counter][i] = Double.valueOf(numbers[i]);
                    }
                    counter++;
                }
            }
            return result;
        }
        catch(IOException | NumberFormatException e){
            e.printStackTrace();
            return null;
        }
    }

    public void generateMatrix(String fileName, String separator) {
        try(FileWriter fileWriter = new FileWriter("./resources/" + (hashCode + fileName.hashCode()) + ".txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
                bufferedWriter.write(this.line + separator + this.column + '\n');
            for (int l = 0; l < line; l++){
                for (int c = 0; c < column; c++){
                    matriz[l][c] = rand.nextDouble() * 1000;
                    if(c != column - 1)
                        bufferedWriter.write(String.valueOf(matriz[l][c]) + separator);
                    else
                        bufferedWriter.write(String.valueOf(matriz[l][c]));
                }
                bufferedWriter.write("\n");
            }
            Main.files.put(fileName, hashCode + fileName.hashCode());
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
