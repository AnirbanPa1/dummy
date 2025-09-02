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
                .addAnnotatedClass(PerBook.class)
                .addAnnotatedClass(Fictional.class)
                .addAnnotatedClass(NonFictional.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            addBook("SQL", "some", "one", "someone@gmail.com");
            addFictionalBook("Silent Patient", "Alex", "Michelidis", "alex.michelidis@gmail.com", "Thriller, Suspense");
            addFictionalBook("The Kite Runner", "Khaled", "Hosseini", "khaled.hosseini@gmail.com", "Slice of Life, Sad");
            addNonFictionalBook("Sapiens: A Brief History of Humankind", "Yuval", "N. Harari", "n.harari.yuval@gmail.com", "Science");

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
            PerBook book = new PerBook();
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

    public static void addFictionalBook(String name, String a_firstName, String a_lastName, String email, String genre) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Fictional book = new Fictional();
            book.setBookName(name);
            Author author = new Author(a_firstName, a_lastName, email);
            book.setAuthor(author);
            book.setGenre(genre);
            session.save(book);
            session.getTransaction().commit();
            System.out.println("Fictional Book '" + name + "' saved successfully.");
        } finally {
            session.close();
        }
    }

    public static void addNonFictionalBook(String name, String a_firstName, String a_lastName, String email, String subject) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            NonFictional book = new NonFictional();
            book.setBookName(name);
            Author author = new Author(a_firstName, a_lastName, email);
            book.setAuthor(author);
            book.setSubject(subject);
            session.save(book);
            session.getTransaction().commit();
            System.out.println("Non-Fictional Book '" + name + "' saved successfully.");
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