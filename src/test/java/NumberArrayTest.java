import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class NumberArrayTest {

    private NumberArray bigIntArray;

    private List<Long> millionRandomNumbers;

    private NumberArray millionNumberArray;

    private NumberArray oneNumberArray;

    private static final long randomGeneratorSeed = 404187099;

    @BeforeEach
    void setUp() {
        List<BigInteger> bigTestNumbers = Arrays.asList(
                new BigInteger("99910466442067723989062"),
                new BigInteger("9686262510568382493593146"),
                new BigInteger("3403471529440652347632047"),
                new BigInteger("5121390171256508355804764"),
                new BigInteger("9755311462272559841826222"),
                new BigInteger("7079860664631046085622488"),
                new BigInteger("379025208354021833022372"),
                new BigInteger("4212758076154236527165305"),
                new BigInteger("3472623840154729421534686"),
                new BigInteger("5134921405414240261663001"),
                new BigInteger("8590423362795123559837605"),
                new BigInteger("9704727246133896488142775"),
                new BigInteger("5886906603670170191058931"),
                new BigInteger("9367517164578161892475095"),
                new BigInteger("8925272805381988751869638")
        );
        this.bigIntArray = new NumberArray(bigTestNumbers);

        long[] millionRandomNumbers = LongStream.generate(() -> new Random(randomGeneratorSeed).nextLong()).limit(1000000).toArray();
        this.millionRandomNumbers = Arrays.stream(millionRandomNumbers).boxed().collect(Collectors.toList());
        this.millionNumberArray = new NumberArray(millionRandomNumbers);

        long[] numbers = new long[] {15};
        this.oneNumberArray = new NumberArray(numbers);
    }

    @Test
    void testMinOneNumber() {
        assertEquals(BigInteger.valueOf(15), this.oneNumberArray._min());
    }

    @Test
    void testMaxOneNumber() {
        assertEquals(BigInteger.valueOf(15), this.oneNumberArray._max());
    }

    @Test
    void testSumOneNumber() {
        assertEquals(BigInteger.valueOf(15), this.oneNumberArray._sum());
    }

    @Test
    void testMultOneNumber() {
        assertEquals(BigInteger.valueOf(15), this.oneNumberArray._mult());
    }

    @Test
    void testMinMillionRandomNumbers() {
        BigInteger expectedMin = BigInteger.valueOf(Collections.min(millionRandomNumbers));
        assertEquals(expectedMin, millionNumberArray._min());
    }

    @Test
    void testMaxMillionRandomNumbers() {
        BigInteger expectedMax = BigInteger.valueOf(Collections.max(millionRandomNumbers));
        assertEquals(expectedMax, millionNumberArray._max());
    }

    @Test
    void testSumMillionRandomNumbers() {
        BigInteger expectedSum = BigInteger.ZERO;

        for (long num : millionRandomNumbers) {
            expectedSum = expectedSum.add(BigInteger.valueOf(num));
        }

        assertEquals(expectedSum, millionNumberArray._sum());
    }

    @Test
    void testMult() {
        long[] numbers = new long[] {-99560, -99190, -93152, -86618, -82531, -81429, -78704, -72438, -68945, -62848, -57390, -51499, -49548, -44143, -39679, -37413, -37193, -34750, -32650, -30772, -30330, -27628, -24626, -23378, -17183, -12181, -8748, -5896, 8968, 11584, 24595, 25130, 25828, 34311, 34480, 34978, 39238, 46268, 49171, 59746, 63239, 65010, 66896, 69943, 73250, 78277, 80076, 92340, 99196, 99401};
        NumberArray numberArray = new NumberArray(numbers);

        BigInteger expectedProduct = BigInteger.ONE;

        for (long num : numbers) {
            expectedProduct = expectedProduct.multiply(BigInteger.valueOf(num));
        }

        assertEquals(expectedProduct, numberArray._mult());
    }

    @Test
    void testMinBigIntegers() {
        BigInteger expectedMin = new BigInteger("99910466442067723989062");
        assertEquals(expectedMin, bigIntArray._min());
    }

    @Test
    void testMaxBigIntegers() {
        BigInteger expectedMax = new BigInteger("9755311462272559841826222");
        assertEquals(expectedMax, bigIntArray._max());
    }

    @Test
    void testSumBigIntegers() {
        BigInteger expectedSum = new BigInteger("90820382517247785775237137");
        assertEquals(expectedSum, bigIntArray._sum());
    }

    @Test
    void testMultBigIntegers() {
        BigInteger expectedProduct = new BigInteger("1361132049359168933071367403738765309809217780170317727196267513282050267476330521532978188075203495957240127722948864546735642413782635415090100715697581805502672233671708469733076895911045898112374846177817339228870114835779847997243186154714616292332947455639130534546143720761730526054737724977118067820899329383711301698613450995612131802241131110725214453107200000");
        assertEquals(expectedProduct, bigIntArray._mult());
    }

    @Test
    void testCreateFromNonexistentFile() {
        assertThrows(
                IOException.class,
                () -> NumberArray.createFromFile("/<>:*?//////|\"")
        );
    }

    @Test
    void testCreateFromFile() throws IOException {

        File tempFile = File.createTempFile("numbers", ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        writer.write("123 456 789 -12");
        writer.close();

        NumberArray numberArray = NumberArray.createFromFile(tempFile.getPath());

        long[] numbers = new long[] {123, 456, 789, -12};
        NumberArray expected = new NumberArray(numbers);

        assertEquals(expected._min(), numberArray._min());
        assertEquals(expected._max(), numberArray._max());
        assertEquals(expected._sum(), numberArray._sum());
        assertEquals(expected._mult(), numberArray._mult());

        tempFile.delete();
    }
}