package com.example.demo;

import static com.example.demo.ServicioPersona.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@SpringBootApplication
public class HibernateOrmApplication {
    
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        
        ServicioPersona servicioPersona = new ServicioPersona();
        String hql;
            //SpringApplication.run(HibernateOrmApplication.class, args);
        
        // USANDO ENTITYMANAGER:        
            hql = "SELECT p FROM Persona p";
            EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("conexion_Personas");
            EntityManager entityManager = fabrica.createEntityManager();

            Query query = entityManager.createQuery(hql);
            List<Persona> persona = query.getResultList();

                System.out.println("\n* * * * *   INICIO DE LA TABLA INICIAL  * * * * *\n");
                for (Persona p: persona) {
                    System.out.println (p);
                }
                System.out.println("\n* * * * *   FINAL DE LA TABLA INICIAL  * * * * *\n");
                
        // CONSULTAS HQL
        
        System.out.println("\n* * * * * * * * * * * * INICIO: CONSULTA HQL 01 * * * * * * * * * * * *\n"); 
        imprimeListaPersonas(servicioPersona.consultaHQL_01());
        System.out.println("\n* * * * * * * * * * * * FINAL: CONSULTA HQL 01 * * * * * * * * * * * *\n");
        
        System.out.println("\n* * * * * * * * * * * * INICIO: CONSULTA HQL 02 * * * * * * * * * * * *\n");
        servicioPersona.consultaHQL_02();
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * FINAL: CONSULTA HQL 02 * * * * * * * * * * * *\n");
        
        System.out.println("\n* * * * * * * * * * * * INICIO: CONSULTA HQL 03 * * * * * * * * * * * *\n");
        servicioPersona.consultaHQL_03();
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * FINAL: CONSULTA HQL 03 * * * * * * * * * * * *\n");
        
        
        
        System.out.println("\n* * * * * * * * * * * * INICIO: CREACION NUEVO REGISTRO Irene * * * * * * * * * * * *\n");    
        Integer idPersona = servicioPersona.nuevaPersona("Irene", "Montero", "motero@iglesias.com", "985698510"); 
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * FINAL: CREACION NUEVO REGISTRO Irene * * * * * * * * * * * *\n");

        System.out.println("\n* * * * * * * * * * * * INICIO: ACTUALIZAR EMAIL REGISTRO motero@iglesias.es * * * * * * * * * * * *\n");
        servicioPersona.actualizaPersona(idPersona, "motero@iglesias.es");    
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * FINAL: ACTUALIZAR EMAIL REGISTRO motero@iglesias.es * * * * * * * * * * * *\n");
        
        System.out.println("\n* * * * * * * * * * * * INICIO: BORRAR REGISTRO Irene * * * * * * * * * * * *\n");
        servicioPersona.borraPersona(idPersona);
        imprimeListaPersonas(servicioPersona.listaPersonas()); 
        System.out.println("\n* * * * * * * * * * * * FINAL: BORRAR REGISTRO Irene * * * * * * * * * * * *\n");

    }     
}