// Part 1: Class & Inheritance
class Employee extends Person {
    private String department;
    private double salary;

    // Default constructor
    public Employee() {}

    // Parameterized constructor
    public Employee(String name, int age, String id, String department, double salary) {
        super(name, age, id);
        this.department = department;
        this.salary = salary;
    }

    // Getter/Setter
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String getPersonType() {
        return "Employee";
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, id='%s', department='%s', salary=%.2f}",
                getName(), getAge(), getId(), department, salary);
    }
}