import java.util.Date;

// Main Class (Demonstrate All Features)
public class AssignmentTwo {
    public static void main(String[] args) {
        // Initialize test data
        Employee operator = new Employee("John Doe", 30, "EMP001", "Ride Operations", 5000.0);
        Ride rollerCoaster = new Ride("Roller Coaster", 10, operator, 3); // Max queue size:10, 3 riders/cycle

        partThree(rollerCoaster);    // Test Queue Functions
        partFourA(rollerCoaster);    // Test History Functions
        partFourB(rollerCoaster);    // Test History Sorting
        partFive(rollerCoaster);     // Test Cycle Operation
        partSix(rollerCoaster);      // Test File Export
        partSeven(rollerCoaster);    // Test File Import
    }

    // Part 3: Queue Function Demonstration
    public static void partThree(Ride ride) {
        System.out.println("\n==================== Part 3: Waiting Queue Demo ====================");
        // Add 5 visitors
        Date now = new Date();
        ride.addVisitorToQueue(new Visitor("Alice", 20, "VIS001", "TICK001", now));
        ride.addVisitorToQueue(new Visitor("Bob", 25, "VIS002", "TICK002", now));
        ride.addVisitorToQueue(new Visitor("Charlie", 18, "VIS003", "TICK003", now));
        ride.addVisitorToQueue(new Visitor("David", 30, "VIS004", "TICK004", now));
        ride.addVisitorToQueue(new Visitor("Eve", 22, "VIS005", "TICK005", now));

        // Print queue
        ride.printQueue();

        // Remove 1 visitor
        ride.removeVisitorFromQueue();

        // Print queue after removal
        ride.printQueue();
    }

    // Part 4A: Ride History Demonstration
    public static void partFourA(Ride ride) {
        System.out.println("\n==================== Part 4A: Ride History Demo ====================");
        Date now = new Date();
        // Add 5 visitors to history
        Visitor v1 = new Visitor("Frank", 28, "VIS006", "TICK006", now);
        Visitor v2 = new Visitor("Grace", 23, "VIS007", "TICK007", now);
        Visitor v3 = new Visitor("Henry", 35, "VIS008", "TICK008", now);
        Visitor v4 = new Visitor("Ivy", 19, "VIS009", "TICK009", now);
        Visitor v5 = new Visitor("Jack", 27, "VIS010", "TICK010", now);

        ride.addVisitorToHistory(v1);
        ride.addVisitorToHistory(v2);
        ride.addVisitorToHistory(v3);
        ride.addVisitorToHistory(v4);
        ride.addVisitorToHistory(v5);

        // Check if a visitor exists in history
        Visitor checkVisitor = new Visitor("Grace", 23, "VIS007", "", null);
        System.out.printf("%nIs visitor [%s] in history: %b%n", checkVisitor.getName(), ride.checkVisitorFromHistory(checkVisitor));

        // Print history count and all visitors
        System.out.printf("Total visitors in history: %d%n", ride.numberOfVisitors());
        ride.printRideHistory();
    }

    // Part 4B: History Sorting Demonstration
    public static void partFourB(Ride ride) {
        System.out.println("\n==================== Part 4B: History Sorting Demo ====================");
        System.out.println("\nBefore sorting:");
        ride.printRideHistory();

        // Sort by age using comparator
        ride.sortHistory(new VisitorAgeComparator());

        System.out.println("\nAfter sorting by age (ascending):");
        ride.printRideHistory();
    }

    // Part 5: Cycle Operation Demonstration
    public static void partFive(Ride ride) {
        System.out.println("\n==================== Part 5: Cycle Operation Demo ====================");
        // Add more visitors to queue (replenish after Part3 removal)
        Date now = new Date();
        ride.addVisitorToQueue(new Visitor("Kelly", 24, "VIS011", "TICK011", now));
        ride.addVisitorToQueue(new Visitor("Leo", 29, "VIS012", "TICK012", now));
        ride.printQueue();

        // Run first cycle
        ride.runOneCycle();

        // Run second cycle
        ride.runOneCycle();

        // Print queue and history after cycles
        System.out.println("\nQueue after 2 cycles:");
        ride.printQueue();

        System.out.println("\nHistory after 2 cycles:");
        ride.printRideHistory();
    }

    // Part 6: File Export Demonstration
    public static void partSix(Ride ride) {
        System.out.println("\n==================== Part 6: File Export Demo ====================");
        String filePath = "ride_history.csv";
        ride.exportRideHistory(filePath);
    }

    // Part 7: File Import Demonstration
    public static void partSeven(Ride ride) {
        System.out.println("\n==================== Part 7: File Import Demo ====================");
        String filePath = "ride_history.csv";

        // Clear history to simulate recovery scenario
        ride = new Ride(ride.getRideName(), ride.getMaxCapacity(), ride.getOperator(), ride.getMaxRider());
        System.out.println("\nHistory after clearing:");
        ride.printRideHistory();

        // Import from file
        ride.importRideHistory(filePath);

        // Print imported history
        System.out.println("\nHistory after import:");
        ride.printRideHistory();
    }
}