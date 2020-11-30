import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;


public class Main {

    public static void main(String args[]) {
        try {

            Random random = new Random();

            int numberOfTests = 20;

            int[] sizes = new int[numberOfTests];

            int size = 0;

            for (int sizePosition = 0; sizePosition < sizes.length; sizePosition++) {
                int currentSize = (int) Math.pow(2, random.nextInt(26 - 15) + 15); // the size will be 2 in the power a number between 15 and 26
                sizes[sizePosition] = currentSize;
                if (size < currentSize)
                    size = currentSize;
            }

            int[][] arrays = new int[numberOfTests][size];

            for (int test = 0; test < numberOfTests; test++) {
                for (int numberPosition = 0; numberPosition < sizes[test]; numberPosition++) {
                    arrays[test][numberPosition] = random.nextInt(999999999);
                }
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String name = now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth() + " " + now.getHour() + "_" + now.getMinute() + "_" + now.getSecond() + ".csv";
            File csvResult = new File(name);
            FileWriter fileWriter = new FileWriter(name);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Size;Time to Finish (ms)" + '\n');


            QuickSortMutliThreading qsmt;
            for (int i = 0; i < numberOfTests; i++) {
                System.out.println("Started sorting on test " + (i + 1));
                long start = System.currentTimeMillis();
                qsmt = new QuickSortMutliThreading(0, sizes[i] - 1, arrays[i], new int[]{1}, new Semaphore(Runtime.getRuntime().availableProcessors() - 4));
                qsmt.start();
                qsmt.join();
                long end = System.currentTimeMillis();
                bufferedWriter.write(sizes[i] + ";" + (end - start) + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}