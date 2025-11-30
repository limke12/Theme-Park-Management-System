// Part 2: Interface Definition
interface RideInterface {
    void addVisitorToQueue(Visitor visitor);
    Visitor removeVisitorFromQueue();
    void printQueue();
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();
    void runOneCycle();
}