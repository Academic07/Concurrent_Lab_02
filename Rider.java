import java.util.concurrent.atomic.AtomicInteger;

public class Rider implements Runnable {
    private static AtomicInteger riderId = new AtomicInteger(0);  
    private int id;
    private BusStop busStop;

    public Rider(BusStop busStop) {
        this.id = riderId.getAndIncrement();  
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
            Thread.currentThread().interrupt();  
        }
    }
}
