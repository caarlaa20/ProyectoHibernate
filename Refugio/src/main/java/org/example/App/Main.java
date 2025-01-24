package org.example.App;

import org.example.DAO.AnimalDAOImpl;
import org.example.DAO.FamiliaDAOImpl;
import org.example.Entities.Animal;
import org.example.Entities.Familia;
import org.example.Entities.EstadoAnimal;
import org.example.Util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnimalDAOImpl animalDAO = new AnimalDAOImpl(HibernateUtil.getSessionFactory());
        FamiliaDAOImpl familiaDAO = new FamiliaDAOImpl(HibernateUtil.getSessionFactory());

        while (true) {
            System.out.println("1. Registrar Animal");
            System.out.println("2. Buscar por Especie");
            System.out.println("3. Actualizar Estado");
            System.out.println("4. Eliminar Animal");
            System.out.println("5. Registrar Familia");
            System.out.println("6. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:  // Registrar un nuevo animal
                    System.out.print("Nombre del animal: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Especie del animal: ");
                    String especie = scanner.nextLine();
                    System.out.print("Edad del animal: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descripción del animal: ");
                    String descripcion = scanner.nextLine();

                    Animal nuevoAnimal = new Animal();
                    nuevoAnimal.setNombre(nombre);
                    nuevoAnimal.setEspecie(especie);
                    nuevoAnimal.setEdad(edad);
                    nuevoAnimal.setDescripcion(descripcion);

                    animalDAO.guardarAnimal(nuevoAnimal);
                    System.out.println("Animal registrado con éxito.");
                    break;

                case 2:  // Buscar animales por especie
                    System.out.print("Especie para buscar: ");
                    String especieBuscar = scanner.nextLine();
                    List<Animal> animales = animalDAO.obtenerAnimalesPorEspecie(especieBuscar);
                    if (animales.isEmpty()) {
                        System.out.println("No se encontraron animales de esa especie.");
                    } else {
                        for (Animal animal : animales) {
                            System.out.println("ID: " + animal.getId() + ", Nombre: " + animal.getNombre() + ", Estado: " + (animal.getEstado() != null ? animal.getEstado().getNombre() : "No asignado"));
                        }
                    }
                    break;

                case 3:  // Actualizar el estado de un animal
                    System.out.print("ID del animal a actualizar: ");
                    int animalId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nuevo estado del animal: ");
                    String nuevoEstado = scanner.nextLine();

                    EstadoAnimal estadoAnimal = new EstadoAnimal();
                    estadoAnimal.setNombre(nuevoEstado);

                    animalDAO.actualizarAnimalEstado(animalId, estadoAnimal);
                    System.out.println("Estado del animal actualizado.");
                    break;

                case 4:  // Eliminar un animal
                    System.out.print("ID del animal a eliminar: ");
                    int eliminarId = scanner.nextInt();
                    animalDAO.eliminarAnimal(eliminarId);
                    System.out.println("Animal eliminado.");
                    break;

                case 5:  // Registrar una familia
                    System.out.print("Nombre de la familia: ");
                    String nombreFamilia = scanner.nextLine();
                    System.out.print("Edad del miembro principal: ");
                    int edadFamilia = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ciudad de residencia: ");
                    String ciudadFamilia = scanner.nextLine();

                    Familia nuevaFamilia = new Familia();
                    nuevaFamilia.setNombre(nombreFamilia);
                    nuevaFamilia.setEdad(edadFamilia);
                    nuevaFamilia.setCiudad(ciudadFamilia);

                    familiaDAO.guardarFamilia(nuevaFamilia);
                    System.out.println("Familia registrada con éxito.");
                    break;

                case 6:  // Salir
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
