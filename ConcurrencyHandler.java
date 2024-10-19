import java.util.concurrent.Semaphore;

public class ConcurrencyHandler {
    private Semaphore mutex;

    public ConcurrencyHandler() {
        this.mutex = new Semaphore(1); // Semaphore with a single permit (mutex)
    }

    public void acquireMutex() throws InterruptedException {
        mutex.acquire();
    }

    public void releaseMutex() {
        mutex.release();
    }
}
