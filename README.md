# Senate Bus Problem Implementation

## Overview

This project implements a solution to the Senate Bus Problem, a classic synchronization problem in computer science. The problem simulates a bus stop where riders arrive randomly and buses arrive periodically to pick up waiting riders.

## Problem Description

- Riders arrive at a bus stop and wait for a bus.
- When a bus arrives, it picks up all waiting riders, up to its capacity of 50 riders.
- If more than 50 riders are waiting, the excess must wait for the next bus.
- New riders arriving while a bus is boarding must wait for the next bus.
- If no riders are waiting when a bus arrives, the bus departs immediately.

## Implementation Details

The solution is implemented in Java and consists of the following classes:

1. `BusStop.java`: Manages the bus stop, including rider queuing and bus arrivals.
2. `Rider.java`: Represents individual riders.
3. `Bus.java`: Simulates bus behavior.
4. `RiderGenerator.java`: Generates new riders at random intervals.
5. `BusProblem.java`: The main class that sets up and runs the simulation.

## Key Features

- Uses an `ArrayDeque` as a FIFO queue for waiting riders.
- Employs semaphores for synchronization between riders and buses.
- Simulates random arrival times using exponential distributions:
  - Bus inter-arrival time: mean of 15 minutes
  - Rider inter-arrival time: mean of 20 seconds
- Runs the simulation for 1 hour.

## How to Compile and Run

1. Ensure you have Java Development Kit (JDK) installed on your system.

2. Open a terminal or command prompt and navigate to the directory containing the Java files.

3. Compile all Java files:
   ```
   javac *.java
   ```

4. Run the main class:
   ```
   java SenateBusProblem
   ```

## Output

The program will print messages to the console indicating:
- When riders arrive at the bus stop
- When buses arrive
- Which riders board the bus
- When buses depart

The simulation will run for 1 hour and then terminate automatically.
