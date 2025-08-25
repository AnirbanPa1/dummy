package org.openarcadia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    private static SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("config.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            addBook("SQL");
            addBook("Java");

            List<String> books = getAllBookNames();
            System.out.println("Books in DB: " + books);
        } finally {
            factory.close();
        }
    }

    public static void addBook(String name) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Book book = new Book();
            book.setBookName(name);
            session.save(book);
            session.getTransaction().commit();
            System.out.println("Book '" + name + "' saved successfully.");
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<String> getAllBookNames() {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            List<String> books = session.createQuery("SELECT b.bookName FROM Book b").list();
            session.getTransaction().commit();
            return books;
        } finally {
            session.close();
        }
    }
}