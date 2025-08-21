package org.openarcadia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("config.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();


        Session session = factory.openSession();


        try {
            session.beginTransaction();
            Book b1 = new Book();
            b1.setBookName("SQL");
            session.save(b1);
            session.getTransaction().commit();
            System.out.println("Book saved successfully");
        } finally {
            session.close();
            factory.close();
        }
    }
}
