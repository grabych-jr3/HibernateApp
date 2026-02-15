package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();

            Person person1 = new Person("testName1", 19);
            Person person2 = new Person("testName2", 23);
            session.persist(person1);
            session.persist(person2);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
