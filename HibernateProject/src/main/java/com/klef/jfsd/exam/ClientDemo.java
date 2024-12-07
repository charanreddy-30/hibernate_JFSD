package com.klef.jfsd.exam;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Insert records manually
        Department dept1 = new Department();
        dept1.setName("Computer Science");
        dept1.setLocation("Hyderabad");
        dept1.setHodName("Dr. Smith");

        Department dept2 = new Department();
        dept2.setName("Electronics");
        dept2.setLocation("Chennai");
        dept2.setHodName("Prof. Patel");
        tx.commit();

        // Delete records using HQL with positional parameters
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Department ID to delete: ");
        int departmentIdToDelete = scanner.nextInt();

        tx = session.beginTransaction();
        String hql = "DELETE FROM Department WHERE departmentId = ?";
        int result = session.createQuery(hql)
                .setParameter(1, departmentIdToDelete)
                .executeUpdate();
        tx.commit();

        System.out.println(result + " record(s) deleted.");

        session.close();
        factory.close();
        scanner.close();
    }
}