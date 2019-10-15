import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Language3Recognizer {
    /*
    <S>  <A>a<B>b
    <A>  <A>b | b
    <B>  a<B> | d
    */
    private static Tokenizer tokenizer;

    /**
     *
     * @param datafile
     * @return
     */
    public static void readData(String datafile) throws IOException {
        tokenizer = new Tokenizer();
        boolean state=true;
        BufferedReader bufferedReader=new BufferedReader(new FileReader(datafile));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            tokenizer.storeTokenizableString(line.trim());
            boolean result = S();
            printLine(line.trim()+" : "+result);
        }
    }

    private static void printLine(String output){
        System.out.println(output);
    }

    private static boolean S(){
        String current  = tokenizer.getCurrentToken();
        if(current.equalsIgnoreCase("b")){
            while (true){
                boolean result = A();
                if(!result){
                    break;
                }
            }

            if(tokenizer.getCurrentToken().equalsIgnoreCase("a")){
                tokenizer.getNextToken();
                boolean result = B();
                if(result){
                    return tokenizer.getCurrentToken().equalsIgnoreCase("b") && tokenizer.getNextToken().equalsIgnoreCase("#");
                }else{
                    return tokenizer.getCurrentToken().equalsIgnoreCase("b");
                }
            }else{
                return false;
            }
        }
        return false;
    }

    private static boolean A(){
        if(tokenizer.getCurrentToken().equalsIgnoreCase("b")){
            tokenizer.getNextToken();
            return true;
        }else{
            return false;
        }
    }

    private static boolean B(){
        if(tokenizer.getCurrentToken().equalsIgnoreCase("d")){
            tokenizer.getNextToken();
            return true;
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("a")){
            tokenizer.getNextToken();
            return B();
        }else{
            return false;
        }
    }
}

