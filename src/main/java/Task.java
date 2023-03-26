import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    private String description;

    @ManyToMany(mappedBy = "tasks")
    private Set<Employee> employeeList = new HashSet<>();

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        return id != null && id.equals(((Task) o).getId());
    }

    public Set<Employee> getEmployeeSet() {
        return employeeList;
    }

    public void setEmployeeSet(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee emp) {
        employeeList.add(emp);
        emp.addTask(this);
    }

    public void removeEmployee(Employee emp) {
        employeeList.remove(emp);
        emp.removeTask(this);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
