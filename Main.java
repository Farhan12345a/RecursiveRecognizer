import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Grammar Parser");
        System.out.println("Choose the language to recognize: ");
        System.out.println("1. Language 1");
        System.out.println("2. Language 2");
        System.out.println("3. Language 3");
        int number=1;
        while(true){
            try {
                number = Integer.parseInt(scanner.nextLine());
                if(number>=1 && number<=3){
                    break;
                }
                System.out.println("Please input a valid number.[1-3]");
            }catch (NumberFormatException n){
                System.out.println("Please input a valid number.[1-3]");
            }
        }
        System.out.println("enter the name of file: ");
        String filename = scanner.nextLine().trim();
        System.out.println("\n");
        testLanguage(number,"data/"+filename);
    }

    public static void testLanguage(int choice, String file){
        try {
            switch (choice){
                case 1:
                    Language1Recognizer.readData(file);
                    break;
                case 2:
                    Language2Recognizer.readData(file);
                    break;
                case 3:
                    Language3Recognizer.readData(file);
                    break;
                default:
                    System.out.println("invalid choice");
            }

        } catch (IOException e) {
            System.out.println("FIle not found.");
        }
    }
}
