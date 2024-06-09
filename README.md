![example workflow](https://github.com/anri-khaskhazyan/TZ2/actions/workflows/maven.yml/badge.svg)

# Простейшая программа на Java для работы с числами

Этот репозиторий содержит простую программу на Java, которая считывает числа из файла и выполняет несколько операций над этими числами. В частности, программа находит минимальное число, максимальное число, вычисляет сумму и произведение чисел.

На вход программа принимает глобальный пусть к текстовому файлу, который содержит числа разделенные пробелом. Максимальное количество чисел - 1 млн.

Сборка:
```mvn clean package```

Запуск: ```java -jar target/TZ2_Maven-1.0-SNAPSHOT.jar```

Для запуска тестов:
```mvn clean test```

Пример работы программы:
```text
# numbers.txt
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
```
Ввод:
```
Enter full path to file
/home/user/Desktop/numbers.txt
```
Результат:
```
Min: 1
Max: 15
Sum: 120
Mult: 1307674368000
```

## Тест скорости работы
![test1](https://github.com/anri-khaskhazyan/TZ2/assets/167525866/1e2e0d9f-fb79-4735-a74a-120e06d4af2b)

В примере была протестирована функция `sum()`. Ось Y - время выполнения в миллисекундах. По оси X - количество элементов массива. Наглядно видно что программа выполняется за время O(n).

Для получения результата использовался следующий код:

```java
int testsCount = 500;
int oneTestRepetitions = 100;
int multiplier = 1000;
long seed = 404187099;

System.out.println("n, O");
for (int i = 1; i < testsCount; i++) {

    File tempFile = File.createTempFile("numbers", ".txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    long[] randomNumbers = LongStream.generate(() -> new Random(seed).nextLong()).limit(i * multiplier).toArray();
    writer.write(Arrays.toString(randomNumbers).replaceAll("[\\[\\],]", ""));
    writer.close();

    NumberArray numberArray = NumberArray.createFromFile(tempFile.getPath());

    long sumTime = 0;
    for (int j = 0; j < oneTestRepetitions; j++) {
        long startTime = System.nanoTime();
        numberArray.sum();
        long endTime = System.nanoTime();
        sumTime += (endTime - startTime) / 1000;
    }

    long avg = sumTime / oneTestRepetitions;
    System.out.println(randomNumbers.length + "," + avg);

    tempFile.delete();
}

```

