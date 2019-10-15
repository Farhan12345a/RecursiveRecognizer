import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language2Recognizer {
    private static Tokenizer tokenizer;
    /*
    <ASSGN>   <ID>=<EXPR>
    <EXPR>   <DIGIT>+<EXPR>  |  <DIGIT>-<EXPR> | <DIGIT>
    <DIGIT>   0 | 1 | 2 | … | 9|
    <ID>  a | b
    */

    /**
     * @param datafile
     * @return
     */
    public static void readData(String datafile) throws IOException {
        tokenizer = new Tokenizer();
        boolean state = true;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(datafile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            tokenizer.storeTokenizableString(line.trim());
            boolean result = assign();
            printLine(line.trim() + " : " + result);
        }
    }

    private static void printLine(String output) {
        System.out.println(output);
    }

    private static boolean assign() {
        if (id()) {
            tokenizer.getNextToken();
            if (tokenizer.getCurrentToken().equalsIgnoreCase("=")) {
                tokenizer.getNextToken();
                return expr();
            }
        }
        return false;
    }

    private static boolean id() {
        String current = tokenizer.getCurrentToken();
        return current.equalsIgnoreCase("a") || current.equalsIgnoreCase("b");
    }

    private static boolean expr() {
        if (digit()) {
            String next = tokenizer.getNextToken();
            if (next.equalsIgnoreCase("#")) {
                return true;
            } else {
                return expr();
            }
        } else {
            String current = tokenizer.getCurrentToken();
            if (current.equalsIgnoreCase("+") || current.equalsIgnoreCase("-") || current.equalsIgnoreCase("*")) {
                tokenizer.getNextToken();
                return expr();
            }else if(current.equalsIgnoreCase("#")){
                return true;
            }
        }

        return false;
    }

    private static boolean digit() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(tokenizer.getCurrentToken());
        return matcher.matches();
    }
}
