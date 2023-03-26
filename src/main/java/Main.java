import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Arrays;
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
        props.put(Environment.HIGHLIGHT_SQL, "true");

        SessionFactory sf = new Configuration()
                .setProperties(props)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Task.class)
                .buildSessionFactory();

        Employee emp1 = new Employee("Oleg");
        Employee emp2 = new Employee("Maleg");
        Employee emp3 = new Employee("Cooleg");

        Task task1 = new Task("TaSk 1", "TaSk 1 DeScription");
        Task task2 = new Task("TaSk 2", "TaSk 2 DeScription");
        Task task3 = new Task("TaSk 3", "TaSk 3 Description");
        Task task4 = new Task("TaSk 4", "TaSk 4 DeScription");
        Task task5 = new Task("TaSk 5", "TaSk 5 DeScription");
        Task task6 = new Task("TaSk 6", "TaSk 6 DeScription");

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(task1);
        session.persist(task2);
        session.persist(task3);
        session.persist(task4);
        session.persist(task5);
        session.persist(task6);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);

        emp1.addTask(task4);
        emp1.addTask(task6);
        emp1.addTask(task2);
        emp2.addTask(task1);
        emp2.addTask(task4);
        emp2.addTask(task3);
        emp2.addTask(task5);
        emp3.addTask(task3);
        emp3.addTask(task4);
        
        task3.addEmployee(emp1);
        task3.removeEmployee(emp2);

        tx.commit();

//        tx.begin();
//
//        Employee emp4 = session.get(Employee.class, 2);
//        emp4.getTasks().clear();
//        session.remove(emp4);
//
//        tx.commit();
        tx.begin();

        Task task15 = session.get(Task.class, 3);
        System.out.println(task15);
        System.out.println(Arrays.toString(task15.getEmployeeSet().toArray()));
        System.out.println(task15.getEmployeeSet().size());

        tx.commit();
//
//        List<Task> taskList = session.createQuery("from Task", Task.class).list();
//        for (Task task : taskList) {
//            System.out.println(task + "; " + task.getName() + "; " + task.getDescription());
//        }
//        tx.commit();
        sf.close();
    }
}
