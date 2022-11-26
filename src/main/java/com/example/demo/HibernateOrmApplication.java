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
            
        // CONSULTAS HQL
            
        imprimeListaPersonas(servicioPersona.consultaHQL_01());
        
        servicioPersona.consultaHQL_02();
        
        // USANDO ENTITYMANAGER:        
            hql = "SELECT p FROM Persona p";
            EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("conexion_Personas");
            EntityManager entityManager = fabrica.createEntityManager();

            Query query = entityManager.createQuery(hql);
            List<Persona> persona = query.getResultList();

                System.out.println("\n* * * * *   TABLA INICIAL  * * * * *\n");
                for (Persona p: persona) {
                    System.out.println (p);
                }
                System.out.println("\n* * * * *   TABLA INICIAL  * * * * *\n");
                
                
        
            
        Integer idPersona = servicioPersona.nuevaPersona("Irene", "Montero", "motero@iglesias.com", "985698510");    

        System.out.println("\n* * * * * * * * * * * * CREACION NUEVO REGISTRO * * * * * * * * * * * *\n");    
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * CREACION NUEVO REGISTRO * * * * * * * * * * * *\n");

        System.out.println("\n* * * * * * * * * * * * ACTUALIZAR EMAIL REGISTRO * * * * * * * * * * * *\n");
        servicioPersona.actualizaPersona(idPersona, "motero@iglesias.es");    
        imprimeListaPersonas(servicioPersona.listaPersonas());
        System.out.println("\n* * * * * * * * * * * * ACTUALIZAR EMAIL REGISTRO * * * * * * * * * * * *\n");

        servicioPersona.borraPersona(idPersona);

        System.out.println("\n* * * * * * * * * * * * BORRAR REGISTRO * * * * * * * * * * * *\n");       
        imprimeListaPersonas(servicioPersona.listaPersonas()); 
        System.out.println("\n* * * * * * * * * * * * BORRAR REGISTRO * * * * * * * * * * * *\n");

    }     
}