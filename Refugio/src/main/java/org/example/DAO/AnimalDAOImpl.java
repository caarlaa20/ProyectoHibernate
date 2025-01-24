package org.example.DAO;

import org.example.Entities.Animal;
import org.example.Entities.EstadoAnimal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    private SessionFactory sessionFactory;

    public AnimalDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Guardamos el animal
    @Override
    public void guardarAnimal(Animal animal) {
        Session session = null;
        Transaction transaction = null;
        try {

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }


    //Obtenemos a los animales por especie
    @Override
    public List<Animal> obtenerAnimalesPorEspecie(String especie) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        List<Animal> animales = null;
        try {
            transaction = session.beginTransaction();

            Query<Animal> query = session.createQuery("from Animal where especie = :especie", Animal.class);
            query.setParameter("especie", especie);
            animales = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animales;
    }


    //Actualizamos el estado del animal
    @Override
    public void actualizarAnimalEstado(int animalId, EstadoAnimal estado) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Primero, asegurémonos de que el EstadoAnimal esté guardado
            if (estado.getId() == 0) {
                session.save(estado);
            }

            // Luego, obtenemos el Animal y actualizamos su estado
            Animal animal = session.get(Animal.class, animalId);
            if (animal != null) {
                animal.setEstado(estado);  // Asignamos el EstadoAnimal
                session.saveOrUpdate(animal);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    //Eliminamos el animal
    @Override
    public void eliminarAnimal(int animalId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Animal animal = session.get(Animal.class, animalId);
            if (animal != null) {
                session.delete(animal);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
