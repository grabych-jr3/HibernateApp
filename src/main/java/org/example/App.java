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

            Person person1 = session.find(Person.class, 2);
            person1.setAge(18);

            Person person2 = session.find(Person.class, 3);
            session.remove(person2);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
