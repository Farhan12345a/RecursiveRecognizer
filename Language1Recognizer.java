import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Language1Recognizer {
    /**
     * <S>-> a<S>c<B> | <A> | b
     * <A>-> c<A> | d
     * <B>-> d | a<A>
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
        if(tokenizer.getCurrentToken().equalsIgnoreCase("a")){
            tokenizer.getNextToken();
            boolean result = S();
            if(result){
                return true;
            }else{
                //return tokenizer.getCurrentToken().equalsIgnoreCase("c");\
                return false;
            }
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("c")){
            tokenizer.getNextToken();
            return B();
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("b")){
            return true;
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("#")){
            return true;
        }else{
            return A();
        }
    }

    private static boolean A(){
        if(tokenizer.getCurrentToken().equalsIgnoreCase("d")){
            tokenizer.getNextToken();
            return true;
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("c")){
            tokenizer.getNextToken();
            return A();
        }else{
            return tokenizer.getCurrentToken().equalsIgnoreCase("#");
        }
    }

    private static boolean B(){
        if(tokenizer.getCurrentToken().equalsIgnoreCase("d")){
            tokenizer.getNextToken();
            return true;
        }else if(tokenizer.getCurrentToken().equalsIgnoreCase("a")){
            tokenizer.getNextToken();
            return A();
        }else{
            return tokenizer.getCurrentToken().equalsIgnoreCase("#");
        }
    }
}
