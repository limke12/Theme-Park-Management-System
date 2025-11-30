import java.util.Comparator;

// Part 4B: Implement Comparator (Sort Visitors by Age)
class VisitorAgeComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}