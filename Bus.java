import java.util.Random;

public class Bus implements Runnable {
    private BusStop busStop;
    private Random random = new Random();
    private double meanArrivalTime = 10 * 60 * 1000;  // Mean arrival time in milliseconds (10 minutes)

    public Bus(BusStop busStop) {
        this.busStop = busStop;
    }

    // Generate an exponentially distributed inter-arrival time
    private long getExponentialInterArrivalTime() {
        return (long) (-Math.log(1 - random.nextDouble()) * meanArrivalTime);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long interArrivalTime = getExponentialInterArrivalTime();  
                Thread.sleep(interArrivalTime);  // Wait for the next bus to arrive
                busStop.busArrives();  // Bus arrives at the bus stop
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }
    }
}
