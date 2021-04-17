package countingthreads;

public class ThreadSpawner {

    private ThreadSpawner() {
    }

    public static void spawnThread(Callback callback) throws InterruptedException {
        var thread = new Thread(callback::onCallBack);
        thread.join();
    }

    public static void main(String[] args) throws InterruptedException {
        spawnThread(PoolService.startLoggerPool(10,1000,10));
        spawnThread(PoolService.startLoggerPool(10,1000,10));
    }
}
