public class Tokenizer {
    private String subject;
    private char[] tokens = null;
    private String current;
    private int currentPosition=-1;

    public Tokenizer(){

    }

    public void storeTokenizableString(String subject){
        this.subject = subject;
        tokens = subject.toCharArray();
        currentPosition=0;
        current = String.valueOf(tokens[0]);
    }

    public String getCurrentToken(){
        return current;
    }

    private boolean endOfString(){
        return currentPosition == tokens.length-1;
    }

    public String getNextToken(){
        if(endOfString()){
            current="#";
            return "#";
        }else{
            current = String.valueOf(tokens[currentPosition+1]);
            currentPosition++;
            return current;
        }
    }
}
