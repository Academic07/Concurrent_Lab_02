import java.util.Random;

public class RiderGenerator implements Runnable {
    private BusStop busStop;
    private Random random = new Random();
    private double meanInterArrivalTime = 30 * 1000;  // Mean arrival time in milliseconds (30seconds)

    public RiderGenerator(BusStop busStop) {
        this.busStop = busStop;
    }

    // Generate an exponentially distributed inter-arrival time
    private long getExponentialInterArrivalTime() {
        return (long) (-Math.log(1 - random.nextDouble()) * meanInterArrivalTime);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long interArrivalTime = getExponentialInterArrivalTime();  
                Thread.sleep(interArrivalTime);  // Wait for the next rider to arrive
                new Thread(new Rider(busStop)).start();  // Create and start a new rider thread
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  
        }
    }
}
