
import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilidades.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateListarWhereMuchos {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Crear la consulta para obtener facultades con ID > ?1
            Query consulta = em.createQuery("SELECT f FROM Facultad f WHERE f.id > ?1", Facultad.class);

            System.out.print("Ingrese un código de facultad: ");
            Long id = Long.parseLong(s.next());  // Convertir la entrada a Long

            // Configurar el parámetro en la consulta
            consulta.setParameter(1, id);

            // Obtener la lista de facultades
            List<Facultad> facultades = consulta.getResultList();

            // Verificar si se encontraron facultades
            if (facultades.isEmpty()) {
                System.out.println("No se encontraron facultades con ID mayor a " + id);
            } else {
                // Iterar sobre las facultades y mostrar sus carreras asociadas
                for (Facultad facultad : facultades) {
                    System.out.println("\nFacultad encontrada: " + facultad);

                    // Consulta para obtener las carreras asociadas a la facultad actual
                    Query consultaCarreras = em.createQuery("SELECT c FROM Carrera c WHERE c.facultad.id = :idFacultad", Carrera.class);
                    consultaCarreras.setParameter("idFacultad", facultad.getId());

                    List<Carrera> carreras = consultaCarreras.getResultList();

                    // Imprimir las carreras asociadas
                    if (carreras.isEmpty()) {
                        System.out.println("No se encontraron carreras para la facultad con ID " + facultad.getId());
                    } else {
                        System.out.println("Carreras asociadas a la facultad:");
                        for (Carrera carrera : carreras) {
                            System.out.println("Carrera: " + carrera.getNombre() + ", Tipo: " + carrera.getTipo());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        } finally {
            // Cerrar el EntityManager
            em.close();
        }
    }
}
