package countingthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class PoolService {

    static void startPool(int amountOfThreads, int delay) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        IntStream.rangeClosed(1, amountOfThreads).forEach(number -> pool.schedule(
                Logger.getLogger(number), (long) number * delay, TimeUnit.MILLISECONDS));
        pool.shutdown();
    }

    public static void main(String[] args) {
        startPool(10,20);
        startPool(10,20);

    }
}
