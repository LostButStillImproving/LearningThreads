package countingthreads;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Logger implements Runnable{

    private final int workerNumber;
    private Logger(int n) {
        workerNumber = n;
    }
    static Logger getLogger(int workerNumber) {
        return new Logger(workerNumber);
    }

    @Override
    public void run() {
        var threadNameArray = Arrays.stream(Thread.currentThread()
                .getName().split("-"))
                .collect(Collectors.toList());

        System.out.printf("Hallo, jeg hedder %s %s. Mit worker thread nummer:  %s.Jeg eksisterer i pool nummer: %s%n",
                this.getClass().getSimpleName(),
                workerNumber, threadNameArray.get(3), threadNameArray.get(1));
    }
}
