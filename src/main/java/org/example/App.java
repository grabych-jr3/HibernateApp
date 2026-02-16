package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App
{
    private static Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

    private static SessionFactory sessionFactory = configuration.buildSessionFactory();

    public static void main( String[] args )
    {
        try{
            Person person1 = getPerson(2);
            System.out.println(person1);

            int idPerson2 = savePerson();
            Person person2 = getPerson(idPerson2);
            System.out.println(person2);

            editPerson(1, 20);
            Person person3 = getPerson(1);
            System.out.println(person3);

            deletePerson(5);
            List<Person> people = showPeople();
            for(Person p : people){
                System.out.println(p);
            }
        } finally {
            sessionFactory.close();
        }
    }

    public static Person getPerson(int id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Person person = session.find(Person.class, id);
        session.getTransaction().commit();
        return person;
    }

    public static int savePerson(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Person person = new Person("Nastya", 19);
        session.persist(person);
        session.getTransaction().commit();
        return person.getId();
    }

    public static void editPerson(int id, int updatedAge){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = session.find(Person.class, id);
        person.setAge(updatedAge);

        session.getTransaction().commit();
    }

    public static void deletePerson(int id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Person person = session.find(Person.class, id);
        session.remove(person);
        session.getTransaction().commit();
    }

    public static List<Person> showPeople(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Person> people = session.createQuery("FROM Person", Person.class).getResultList();
        session.getTransaction().commit();
        return people;
    }
}
