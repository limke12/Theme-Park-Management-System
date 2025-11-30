import java.util.Date;

// Part 1: Class & Inheritance
class Visitor extends Person {
    private String ticketNumber;
    private Date visitDate;

    // Default constructor
    public Visitor() {}

    // Parameterized constructor
    public Visitor(String name, int age, String id, String ticketNumber, Date visitDate) {
        super(name, age, id);
        this.ticketNumber = ticketNumber;
        this.visitDate = visitDate;
    }

    // Getter/Setter
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public Date getVisitDate() { return visitDate; }
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }

    @Override
    public String getPersonType() {
        return "Visitor";
    }

    @Override
    public String toString() {
        return String.format("Visitor{name='%s', age=%d, id='%s', ticketNumber='%s', visitDate=%s}",
                getName(), getAge(), getId(), ticketNumber, visitDate);
    }
}