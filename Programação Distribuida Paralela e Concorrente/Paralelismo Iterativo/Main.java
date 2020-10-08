import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
    }

    public static double[][] readFile(String file, String separator){
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

        }
    }
}