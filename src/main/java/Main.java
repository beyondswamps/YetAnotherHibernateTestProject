import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/db");
        props.put("hibernate.connection.user", "root");
        props.put("hibernate.connection.password", "root");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");

        SessionFactory sf = new Configuration()
                .setProperties(props)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();

        Task task1 = new Task("Code simple persistent example", "Create 2 test classes - Employee and Task");
        Task task2 = new Task("Task 2", "Task 2 description");
        Task task3 = new Task("Task 3", "Task 3 description");
        Task task4 = new Task("Task 4", "Task 4 description");
        Task task5 = new Task("Task 5", "Task 5 description");
        Task task6 = new Task("Task 6", "Task 6 description");

        Employee emp1 = new Employee("Oleg", new ArrayList<Task>(List.of(task4, task3, task6)));
        Employee emp2 = new Employee("Maleg", new ArrayList<Task>(List.of(task5, task1)));
        Employee emp3 = new Employee("Cooleg", new ArrayList<Task>(List.of(task2)));

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        tx.commit();

    }
}
