import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class QuickSortMutliThreading extends Thread {

    private int start, end;
    private int[] array;
    private int[] usedProcessors;
    private Semaphore semaphore;
    private int totalThreads = Runtime.getRuntime().availableProcessors() - 4;

    public QuickSortMutliThreading(int start, int end, int[] array, int[] usedProcessors, Semaphore semaphore) {
        this.start = start;
        this.end = end;
        this.array = array;
        this.usedProcessors = usedProcessors;
        this.semaphore = semaphore;
    }

    private static int genRandom(int min, int max){
        Random r = new Random();

        int randomNum = r.nextInt((max - min)) + min;

        return randomNum;
    }

    private int partition(int start, int end, int[] array) {

        int i = start, f = end;

        int pivot = genRandom(i, f);
        int t = array[f];
        array[f] = array[pivot];
        array[pivot] = t;
        f--;
        while (i <= f) {
            if (array[i] <= array[end]) {
                i++;
                continue;
            }
            if (array[f] >= array[end]) {
                f--;
                continue;
            }
            t = array[f];
            array[f] = array[i];
            array[i] = t;
            f--;
            i++;
        }

        t = array[f + 1];
        array[f + 1] = array[end];
        array[end] = t;
        return f + 1;
    }

    public Integer sort() {
        if (start >= end)
            return null;

        int p = partition(start, end, array);

        QuickSortSingle left = new QuickSortSingle(start, p - 1, array);
        QuickSortSingle right = new QuickSortSingle(p + 1, end, array);

        left.sort();
        right.sort();
        return  null;
    }


    public Integer sortParallel(){
        try {
            if (start >= end)
                return null;

            int p = partition(start, end, array);

            if (usedProcessors[0] <= totalThreads) {
                semaphore.acquire();
                usedProcessors[0]++;

                QuickSortMutliThreading esquerda = new QuickSortMutliThreading(start, p - 1, array, usedProcessors, semaphore);
                QuickSortMutliThreading direita = new QuickSortMutliThreading(p + 1, end, array, usedProcessors, semaphore);


                esquerda.start();
                direita.start();

                esquerda.join();
                direita.join();

                usedProcessors[0]--;
                semaphore.release();
            } else {
                QuickSortMutliThreading esquerda = new QuickSortMutliThreading(start, p - 1, array, usedProcessors, semaphore);
                QuickSortMutliThreading direita = new QuickSortMutliThreading(p + 1, end, array, usedProcessors, semaphore);

                esquerda.sort();
                direita.sort();
            }

            return null;
        }
        catch (InterruptedException e){
            e.printStackTrace();
            return null;
        }
    }

    public void run(){
        try{
            sortParallel();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

}