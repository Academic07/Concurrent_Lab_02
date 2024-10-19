import java.io.FileNotFoundException;
import java.io.PrintStream;

public class BusProblem {
    public static void main(String[] args) {
        // Create a PrintStream to write to the text file
        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream("simulation_output.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to create output file.");
            e.printStackTrace();
            return; 
        }

        // Create a custom output stream to write to both terminal and file
        PrintStream dualOut = new PrintStream(new MultiOutputStream(System.out, fileOut));
        System.setOut(dualOut);

        BusStop busStop = new BusStop();
        
        Thread busThread = new Thread(new Bus(busStop));
        Thread riderGeneratorThread = new Thread(new RiderGenerator(busStop));

        busThread.start();
        riderGeneratorThread.start();

        try {
            // Let the simulation run for 30 minutes (1800 seconds)
            Thread.sleep(1800000);  
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

        
        fileOut.close();
    }
}
