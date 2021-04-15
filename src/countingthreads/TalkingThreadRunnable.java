package countingthreads;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TalkingThreadRunnable implements Runnable {

    int threadNumber;
    TalkingThreadRunnable(int n) {
        threadNumber = n;
    }

    @Override
    public void run() {
        var threadNameArray = Arrays.stream(Thread.currentThread()
                .getName().split("-"))
                .collect(Collectors.toList());

        System.out.printf("Hallo, jeg hedder %s %s. Real thread number:  %s. Pool number: %s%n",
                this.getClass().getSimpleName(),
                threadNumber, threadNameArray.get(3), threadNameArray.get(1));
    }

    public static Callback startPool(int amountOfThreads, int delay) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1000);
        IntStream.rangeClosed(1,amountOfThreads).forEach(number -> pool.schedule(
                new TalkingThreadRunnable(number), (long)number*delay, TimeUnit.MILLISECONDS));

        return pool::shutdown;
    }

    public static void spawnThread(Callback callback) {
        new Thread(callback::onCallBack).start();
    }
    public interface Callback {
        void onCallBack();
    }

    public static void main(String[] args) {
        spawnThread(startPool(10,1000));
        spawnThread(startPool(20,500));
        spawnThread(startPool(40,250));
        spawnThread(startPool(80,125));
    }
}
