import java.util.concurrent.Semaphore;
import java.util.ArrayDeque;

public class BusStop {
    private static final int BUS_CAPACITY = 50;
    private Semaphore mutex = new Semaphore(1);  // Protect critical sections
    private ArrayDeque<Rider> waitingRiders = new ArrayDeque<>();  // Queue of waiting riders using ArrayDeque

    public void addRider(Rider rider) throws InterruptedException {
        mutex.acquire();
        try {
            
                waitingRiders.offer(rider);  // Add rider to the back of the queue
                System.out.println("Rider " + rider.getId() + " arrived. Total waiting: " + waitingRiders.size());       
            
        } finally {
            mutex.release();  
        }
    }

    public void busArrives() throws InterruptedException {
        mutex.acquire();  // Acquire the mutex to ensure safe access
        try {
            int ridersToBoard = Math.min(waitingRiders.size(), BUS_CAPACITY);
            System.out.println("Bus arrived. Waiting riders: " + waitingRiders.size());

            if (ridersToBoard == 0) {
                System.out.println("Bus departing immediately (no riders).");
                return;
            }

            // Board riders onto the bus
            for (int i = 0; i < ridersToBoard; i++) {
                Rider rider = waitingRiders.poll();  
                System.out.println("Rider " + rider.getId() + " boarded the bus.");
            }

            System.out.println("Bus departing with " + ridersToBoard + " riders. Remaining waiting riders: " + waitingRiders.size());
        } finally {
            mutex.release();  
        }
    }
}
