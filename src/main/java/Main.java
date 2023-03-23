import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost:3306/db");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "root");
        props.put(Environment.HBM2DDL_AUTO, "create");
        props.put(Environment.SHOW_SQL, "true");
//        props.put(Environment.FORMAT_SQL, "true");
        props.put(Environment.HIGHLIGHT_SQL, "true");


        SessionFactory sf = new Configuration()
                .setProperties(props)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();

        Task task1 = new Task("TaSk 1", "TaSk 1 DeScription");
        Task task2 = new Task("TaSk 2", "TaSk 2 DeScription");
        Task task3 = new Task("TaSk 3", "TaSk 3 Description");
        Task task4 = new Task("TaSk 4", "TaSk 4 DeScription");
        Task task5 = new Task("TaSk 5", "TaSk 5 DeScription");
        Task task6 = new Task("TaSk 6", "TaSk 6 DeScription");

        Employee emp1 = new Employee("Oleg", new ArrayList<Task>(List.of(task4, task3, task6)));
        Employee emp2 = new Employee("Maleg", new ArrayList<Task>(List.of(task5, task1)));
        Employee emp3 = new Employee("Cooleg", new ArrayList<Task>(List.of(task2)));

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        tx.commit();
        sf.close();

    }
}
