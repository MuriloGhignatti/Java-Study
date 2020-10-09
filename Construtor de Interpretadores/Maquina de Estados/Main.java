import java.util.ArrayList;

public class Main{


    private static char currentChar;
    private static int position;
    private static boolean lastTimeWasOne;
    private static String string;


    private static ArrayList<Token<Character>> tokens = new ArrayList<>();

    public static void main(String[] args) {
        
        start("10010011");
    }


    public static void start(String s){
        string = s;
        currentChar = '\0';
        position = -1;
        lastTimeWasOne = false;
        while(position != s.length() - 1){
            getNextChar();
            getSwitchChar();
            checkFinal();
        }
    }

    public static void getNextChar(){
        currentChar = string.toCharArray()[++position];
    }

    public static void getSwitchChar(){
        switch(currentChar){
            case '0':
                zero();
                break;
            case '1':
                one();
                break;
            default:
                unkown();
                break;
        }
    }

    public static void zero(){
        lastTimeWasOne = false;
        tokens.add(new Token<Character>('0', 0));
    }

    public static void one(){
        lastTimeWasOne = true;
        tokens.add(new Token<Character>('1', 1));
    }

    public static void unkown(){
        lastTimeWasOne = false;
        tokens.add(new Token<Character>('\0', -1));
    }

    public static void checkFinal(){
        if(position == string.length() -1 ){
            if(lastTimeWasOne)
                System.out.println("The String Ends With '11'");
            else
                System.out.println("The String Doesn't Ent With '11'");
        }
    }
}