import java.util.Random;

public class RiderGenerator implements Runnable {
    private BusStop busStop;
    private Random random = new Random();
    private double meanInterArrivalTime = 30 * 10;  

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
                new Thread(new Rider(busStop)).start();  
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  
        }
    }
}
