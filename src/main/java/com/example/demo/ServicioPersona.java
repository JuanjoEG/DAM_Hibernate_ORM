package com.example.demo;

import java.util.List;
import java.util.Iterator;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Juan José Estévez González
 */
public class ServicioPersona {
    
    private static SessionFactory factory;
    
    public static List consultaHQL_01(){
        
        String hql = "FROM Persona";
        Session session = factory.openSession();
        Transaction transaction = null;
        List results = null;
        
        try {
            transaction = session.beginTransaction();
            Query consulta = session.createQuery(hql);
            System.out.println("\n* * * * * * * * * --->   EJECUTANDO consulta.setFirstResult(1)   <--- * * * * * * * * *\n");
            consulta.setFirstResult(1);
            System.out.println("\n* * * * * * * * * --->   EJECUTANDO consulta.setMaxResults(3)   <--- * * * * * * * * *\n");
            consulta.setMaxResults(3);
            results = consulta.getResultList();
            transaction.commit();
            return results;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }      
        return results;
    }
    
    public static void consultaHQL_02(){
    
        String hql = "update Persona set EMAIL=:email where NOMBRE=:nombre and APELLIDO=:apellido";
        Session session = factory.openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            Query consulta = session.createQuery(hql);
        
            consulta.setParameter("email", "elonmusk@twitter.com");
            consulta.setParameter("nombre", "Elon");
            consulta.setParameter("apellido", "Musk");
            
            int status = consulta.executeUpdate();
            transaction.commit();
            if (status > 0) {
                System.out.println("\n* * * * * * * * * --->   UPDATE REALIZADO email elonmusk@twitter.com   <--- * * * * * * * * *\n");
            } else {
                System.out.println("\n* * * * * * * * * --->   UPDATE NO REALIZADO email elonmusk@twitter.com   <--- * * * * * * * * *\n");
            }
            
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public static void consultaHQL_03(){
    
        String hql = "update Persona set EMAIL=:email where NOMBRE=:nombre and APELLIDO=:apellido";
        Session session = factory.openSession();
        Transaction transaction = null;
        
        try {
            transaction = session.beginTransaction();
            Query consulta = session.createQuery(hql);
        
            consulta.setParameter("email", "elonmusk@tesla.com");
            consulta.setParameter("nombre", "Elon");
            consulta.setParameter("apellido", "Musk");
            
            int status = consulta.executeUpdate();
            transaction.commit();
            if (status > 0) {
                System.out.println("\n* * * * * * * * * --->   UPDATE REALIZADO email elonmusk@tesla.com   <--- * * * * * * * * *\n");
            } else {
                System.out.println("\n* * * * * * * * * --->   UPDATE NO REALIZADO email elonmusk@tesla.com   <--- * * * * * * * * *\n");
            }
            
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public ServicioPersona() {
        try {
            // De esta forma, Java busca el fichero de configuracion del mapeo:
            factory = new Configuration().configure().buildSessionFactory();
            // De esta forma, Java busca las anotaciones en la declaracion de la clase:
            //factory = new AnnotationConfiguration().configure().addAnnotatedClass(Cliente.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Fallo al crear el objeto sessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer nuevaPersona(String nombre, String apellido, String email, String telefono) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Integer idPersona = null;

        try {
            transaction = session.beginTransaction();
            Persona persona = new Persona(nombre, apellido, email, telefono);
            idPersona = (Integer) session.save(persona);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return idPersona;
    }

    public List listaPersonas() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List personas = null;
        try {
            transaction = session.beginTransaction();
            personas = session.createQuery("FROM Persona").list();
            transaction.commit();
            return personas;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personas;
    }    

    public void actualizaPersona(Integer idPersona, String email) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Persona persona = (Persona) session.get(Persona.class, idPersona);
            persona.setEmail(email);
            session.update(persona);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void borraPersona(Integer idPersona) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Persona persona = (Persona) session.get(Persona.class, idPersona);
            session.delete(persona);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void imprimeListaPersonas(List personas) {
        if(!personas.iterator().hasNext()) {
            System.out.println("La lista esta vacia!");
        }
        for (Iterator iterator = personas.iterator(); iterator.hasNext();) {
            Persona persona = (Persona) iterator.next();            
            
            System.out.println("");
            System.out.println("TABLA PERSONA:");
            System.out.println(" Persona ID:  " + persona.getIdPersona());
            System.out.println(" Nombre    :  " + persona.getNombre());
            System.out.println(" Apellido  :  " + persona.getApellido());
            System.out.println(" Email     :  " + persona.getEmail());
            System.out.println(" Telefono  :  " + persona.getTelefono());            
            
        }
    }    
}