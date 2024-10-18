import java.util.Random;

public class Bus implements Runnable {
    private BusStop busStop;
    private Random random = new Random();
    private double meanArrivalTime = 5 * 60 * 100;  // Mean arrival time in milliseconds (20 minutes)

    public Bus(BusStop busStop) {
        this.busStop = busStop;
    }

    // Method to generate an exponentially distributed random value based on the mean
    private long getExponentialInterArrivalTime() {
        return (long) (-Math.log(1 - random.nextDouble()) * meanArrivalTime);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                long interArrivalTime = getExponentialInterArrivalTime();  
                Thread.sleep(interArrivalTime); 
                busStop.busArrives();  
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  
        }
    }
}
