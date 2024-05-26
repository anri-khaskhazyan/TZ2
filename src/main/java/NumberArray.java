import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberArray {


    private final List<BigInteger> numbers;

    public NumberArray() {
        this.numbers = new ArrayList<>();
    }

    public NumberArray(long[] numbers) {
        this.numbers = new ArrayList<>();

        for (long num : numbers) {
            this.numbers.add(BigInteger.valueOf(num));
        }
    }

    public NumberArray(List<BigInteger> numbers) {
        this.numbers = numbers;
    }

    public List<BigInteger> getNumbersList() {
        return this.numbers;
    }

    public static NumberArray createFromFile(String filePath) throws IOException {

        List<BigInteger> numbers = new ArrayList<>();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)));

        while (scanner.hasNext()) {
            numbers.add(new BigInteger(scanner.next()));
        }

        return new NumberArray(numbers);
    }


    public BigInteger min() {

        BigInteger min = numbers.get(0);
        for (BigInteger num : numbers) {
            if (num.compareTo(min) < 0)
                min = num;
        }

        return min;
    }

    public BigInteger max() {

        BigInteger max = numbers.get(0);
        for (BigInteger num : numbers) {
            if (num.compareTo(max) > 0)
                max = num;
        }

        return max;
    }

    public BigInteger sum() {

        BigInteger sum = BigInteger.ZERO;
        for (BigInteger num : numbers) {
            sum = sum.add(num);
        }

        return sum;
    }

    // Название _product подошло бы лучше
    public BigInteger mult() {

        BigInteger mult = BigInteger.ONE;
        for (BigInteger num : numbers) {
            mult = mult.multiply(num);
        }

        return mult;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof NumberArray)) return false;

        NumberArray otherNumberArray = (NumberArray) other;
        return this.numbers.equals(otherNumberArray.getNumbersList());
    }

}
