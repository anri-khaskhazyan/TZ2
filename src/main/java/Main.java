import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter full path to file");
        String filePath = reader.nextLine();

        try {
            NumberArray numberArray = NumberArray.createFromFile(filePath);
            System.out.println("Min: " + numberArray.min());
            System.out.println("Max: " + numberArray.max());
            System.out.println("Sum: " + numberArray.sum());
            System.out.println("Mult: " + numberArray.mult());
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}
