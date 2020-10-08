import java.io.*;

public class Arquivo {





    public static void lerTexto(String pCaminhoArquivo){
        try(BufferedReader buffRead =  new BufferedReader(new FileReader(pCaminhoArquivo))){
            String linha = "";
            while(true){
                if(linha !=null){
                    //System.out.println(linha);
                }else{
                    break;
                }
                linha = buffRead.readLine();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
