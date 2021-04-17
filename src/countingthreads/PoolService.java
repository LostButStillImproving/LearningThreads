package countingthreads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class PoolService {

    public static ThreadSpawner.Callback startLoggerPool(int amountOfThreads, int delay, int maxWorkerThreads) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(maxWorkerThreads);
        IntStream.rangeClosed(1, amountOfThreads).forEach(number -> pool.schedule(
                Logger.getLogger(number), (long) number * delay, TimeUnit.MILLISECONDS));
        pool.shutdown();
        return () -> {};
    }

    public static void main(String[] args) {
        startLoggerPool(10,20,5);
        startLoggerPool(10,20,10);
        ThreadSpawner.spawnThread(startLoggerPool(20,20,20));
    }
}
