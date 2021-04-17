package countingthreads;

public class ThreadSpawner {

    public static void spawnThread(Callback callback) {
        new Thread(callback::onCallBack).start();
    }
    public interface Callback {
        void onCallBack();
    }
}
