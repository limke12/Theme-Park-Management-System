import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Part 1-7: Ride Class (Implements RideInterface)
class Ride implements RideInterface {
    // Instance variables
    private String rideName;
    private int maxCapacity;
    private Employee operator; // Required for Part 5
    private Queue<Visitor> waitingQueue; // Part 3: Waiting Queue
    private LinkedList<Visitor> rideHistory; // Part 4A: Ride History
    private int maxRider; // Part 5: Max riders per cycle
    private int numOfCycles; // Part 5: Number of cycles run

    // Default constructor
    public Ride() {
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = 3; // Default: 3 riders per cycle
        this.numOfCycles = 0;
    }

    // Parameterized constructor
    public Ride(String rideName, int maxCapacity, Employee operator, int maxRider) {
        this();
        this.rideName = rideName;
        this.maxCapacity = maxCapacity;
        this.operator = operator;
        this.maxRider = maxRider;
    }

    // Getter/Setter
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }

    // Part 3: Queue-related Methods Implementation
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (waitingQueue.size() < maxCapacity) {
            waitingQueue.offer(visitor);
            System.out.printf("Added visitor to queue: %s%n", visitor.getName());
        } else {
            System.out.println("Queue is full! Cannot add more visitors.");
        }
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        Visitor removed = waitingQueue.poll();
        if (removed != null) {
            System.out.printf("Removed visitor from queue: %s%n", removed.getName());
        } else {
            System.out.println("Queue is empty! Cannot remove visitors.");
        }
        return removed;
    }

    @Override
    public void printQueue() {
        System.out.printf("%n=== %s Waiting Queue (Current Size: %d) ===%n", rideName, waitingQueue.size());
        if (waitingQueue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingQueue) {
            System.out.printf("%d. %s%n", index++, visitor);
        }
    }

    // Part 4A: History-related Methods Implementation
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.printf("Added visitor to history: %s%n", visitor.getName());
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        // Check by ID (unique identifier)
        return rideHistory.stream()
                .anyMatch(v -> v.getId().equals(visitor.getId()));
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        System.out.printf("%n=== %s Ride History (Total Count: %d) ===%n", rideName, rideHistory.size());
        if (rideHistory.isEmpty()) {
            System.out.println("History is empty");
            return;
        }
        int index = 1;
        for (Visitor visitor : rideHistory) {
            System.out.printf("%d. %s%n", index++, visitor);
        }
    }

    // Part 4B: Sort History Method
    public void sortHistory(Comparator<Visitor> comparator) {
        Collections.sort(rideHistory, comparator);
        System.out.println("\nRide history has been sorted by specified rule");
    }

    // Part 5: Run One Cycle Implementation
    @Override
    public void runOneCycle() {
        System.out.printf("%n=== Starting %s Cycle %d ===%n", rideName, numOfCycles + 1);

        // Check if operator exists
        if (operator == null) {
            System.out.println("Error: No operator assigned! Cannot run cycle.");
            return;
        }

        // Check if queue is empty
        if (waitingQueue.isEmpty()) {
            System.out.println("No visitors in queue! Cycle failed to run.");
            return;
        }

        // Move visitors from queue to history (no more than maxRider)
        int ridersThisCycle = 0;
        while (!waitingQueue.isEmpty() && ridersThisCycle < maxRider) {
            Visitor visitor = removeVisitorFromQueue();
            addVisitorToHistory(visitor);
            ridersThisCycle++;
        }

        numOfCycles++;
        System.out.printf("=== Cycle %d Completed. Riders this cycle: %d ===%n", numOfCycles, ridersThisCycle);
    }

    // Part 6: Export History to CSV File
    public void exportRideHistory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV header
            writer.write("Name,Age,ID,TicketNumber,VisitDate");
            writer.newLine();

            // Write history records
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Visitor visitor : rideHistory) {
                String line = String.format("%s,%d,%s,%s,%s",
                        visitor.getName(),
                        visitor.getAge(),
                        visitor.getId(),
                        visitor.getTicketNumber(),
                        sdf.format(visitor.getVisitDate()));
                writer.write(line);
                writer.newLine();
            }

            System.out.printf("%nSuccessfully exported ride history to: %s%n", filePath);
        } catch (IOException e) {
            System.err.println("Failed to export file: " + e.getMessage());
        }
    }

    // Part 7: Import History from CSV File
    public void importRideHistory(String filePath) {
        // Clear existing history
        rideHistory.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;

            // Read file content
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (lineNum == 1) continue; // Skip header

                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.printf("Skipping invalid line (Line %d): %s%n", lineNum, line);
                    continue;
                }

                // Parse data and create visitor object
                Visitor visitor = new Visitor(
                        parts[0].trim(),
                        Integer.parseInt(parts[1].trim()),
                        parts[2].trim(),
                        parts[3].trim(),
                        sdf.parse(parts[4].trim())
                );
                rideHistory.add(visitor);
            }

            System.out.printf("%nSuccessfully imported %d ride history records from: %s%n", rideHistory.size(), filePath);
        } catch (Exception e) {
            System.err.println("Failed to import file: " + e.getMessage());
        }
    }
}