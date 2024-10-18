public class BusProblem {
    public static void main(String[] args) {
        BusStop busStop = new BusStop();
        
        Thread busThread = new Thread(new Bus(busStop));
        Thread riderGeneratorThread = new Thread(new RiderGenerator(busStop));

        busThread.start();
        riderGeneratorThread.start();

        try {
            // Let the simulation run for 30 minutes (1800 seconds)
            Thread.sleep(3600000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Interrupt both threads to stop the simulation
        busThread.interrupt();
        riderGeneratorThread.interrupt();

        
        try {
            busThread.join();
            riderGeneratorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Simulation ended.");
    }
}
