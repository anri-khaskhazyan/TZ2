import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter full path to file");
        String filePath = reader.nextLine();

        try {
            NumberArray numberArray = NumberArray.createFromFile(filePath);
            System.out.println("Min: " + numberArray._min());
            System.out.println("Max: " + numberArray._max());
            System.out.println("Sum: " + numberArray._sum());
            System.out.println("Mult: " + numberArray._mult());
        } catch (IOException e) {
            System.out.println("File does not exist");
        }
    }
}
