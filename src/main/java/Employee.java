import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(
            cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "employee_id")
    private List<Task> tasks = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }


    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public void addTask(Task task) {
//        tasks.add(task);
//        task.setEmployee(this);
//    }

    public void printTasks() {
        for (Task task : getTasks()) {
            System.out.println(task.getName() + ": " + task.getDescription());
        }
    }
//    public void removeTask(Task task) {
//        tasks.remove(task);
//        task.setEmployee(null);
//    }
}
