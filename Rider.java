import java.util.concurrent.atomic.AtomicInteger;

public class Rider implements Runnable {
    private static AtomicInteger nextId = new AtomicInteger(0);  // Thread-safe ID generator
    private int id;
    private BusStop busStop;

    public Rider(BusStop busStop) {
        this.id = nextId.getAndIncrement();  // Safely generate unique ID
        this.busStop = busStop;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            busStop.addRider(this);  // Rider arrives at the bus stop
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Handle interruption safely
        }
    }
}
