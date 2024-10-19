import java.util.ArrayDeque;

public class BusStop {
    private static final int BUS_CAPACITY = 50;
    private ConcurrencyHandler concurrencyHandler = new ConcurrencyHandler();  
    private ArrayDeque<Rider> waitingRiders = new ArrayDeque<>();  

    public void addRider(Rider rider) throws InterruptedException {
        concurrencyHandler.acquireMutex();
        try {
            waitingRiders.offer(rider);  
            System.out.println("Rider " + rider.getId() + " arrived. Total waiting: " + waitingRiders.size());       
        } finally {
            concurrencyHandler.releaseMutex();  
        }
    }

    public void busArrives() throws InterruptedException {
        concurrencyHandler.acquireMutex();  
        try {
            int ridersToBoard = Math.min(waitingRiders.size(), BUS_CAPACITY);
            System.out.println("Bus arrived. Waiting riders: " + waitingRiders.size());

            if (ridersToBoard == 0) {
                System.out.println("Bus departing immediately (no riders).");
                return;
            }

            for (int i = 0; i < ridersToBoard; i++) {
                Rider rider = waitingRiders.poll();  
                System.out.println("Rider " + rider.getId() + " boarded the bus.");
            }

            System.out.println("Bus departing with " + ridersToBoard + " riders. Remaining waiting riders: " + waitingRiders.size());
        } finally {
            concurrencyHandler.releaseMutex();  
        }
    }
}
