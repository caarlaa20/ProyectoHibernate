package org.example.DAO;

import org.example.Entities.Animal;
import org.example.Entities.EstadoAnimal;

import java.util.List;

public interface AnimalDAO {

    void guardarAnimal(Animal animal);

    List<Animal> obtenerAnimalesPorEspecie(String especie);

    void actualizarAnimalEstado(int animalId, EstadoAnimal estado);

    void eliminarAnimal(int animalId);
}
