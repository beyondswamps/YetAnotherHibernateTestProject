import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "employee_task",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id")
    )
//    @JoinColumn(name = "employee_id")
    private Set<Task> tasks = new HashSet<>();

    public Employee() {
    }

    public Employee(String name, Set<Task> tasks) {
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void printTasks() {
        for (Task task : getTasks()) {
            System.out.println(task.getName() + ": " + task.getDescription());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.getEmployeeSet().add(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.getEmployeeSet().remove(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
