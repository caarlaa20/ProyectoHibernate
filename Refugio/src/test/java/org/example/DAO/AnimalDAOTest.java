package org.example.DAO;

import org.example.Entities.Animal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalDAOTest {

    @Test
    public void testGuardarAnimal() {
        AnimalDAOImpl animalDAO = new AnimalDAOImpl();
        Animal animal = new Animal();
        animal.setNombre("Perro");
        animal.setEspecie("Perro");
        animal.setEdad(3);
        animal.setDescripcion("Perdido cerca del parque");

        animalDAO.guardarAnimal(animal);

        List<Animal> animals = animalDAO.obtenerAnimalesPorEspecie("Perro");
        assertTrue(animals.size() > 0);
    }
}
