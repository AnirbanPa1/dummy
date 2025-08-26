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
            addBook("SQL", "some", "one", "someone@gmail.com");

            List<String> books = getAllBookNames();
            System.out.println("Books in DB: " + books);
        } finally {
            factory.close();
        }
    }

    public static void addBook(String name, String a_firstName, String a_lastName, String email) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Book book = new Book();
            book.setBookName(name);
            Author author = new Author(a_firstName, a_lastName, email);
            book.setAuthor(author);
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