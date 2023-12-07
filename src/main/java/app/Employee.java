package app;

public class Employee {
    private int id;
    private String name;
    private String position;
    private Double salary;
    public Employee(int id, String name, String position, Double salary){
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "\n id: " + id +
                ",\n name: '" + name + '\'' +
                ", \n position: '" + position + '\'' +
                ", \n salary: " + salary + "\n" +
                '}';
    }


}
