
import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import utilidades.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibernateListarWhere {
    public static void main(String[] args) {

        // Se usa Scanner para solicitar un valor por teclado al usuario
        Scanner s = new Scanner(System.in);

        // Importamos el em de EntityManager
        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Se crea la consulta para obtener la facultad por ID
            Query consulta = em.createQuery("select f from Facultad f where f.id = ?1", Facultad.class);

            System.out.print("Ingrese un código de facultad: ");
            Long id = Long.parseLong(s.next());  // Convertir la entrada a Long para coincidir con el tipo de la columna

            // Configurar el parámetro en la consulta
            consulta.setParameter(1, id);

            // Obtener la facultad de la consulta
            Facultad facultad = (Facultad) consulta.getSingleResult();

            // Imprimir la información de la facultad
            System.out.println("Facultad encontrada: " + facultad);

            // Realizar la consulta para obtener las carreras asociadas a la facultad
            Query consultaCarreras = em.createQuery("select c from Carrera c where c.facultad.id = :idFacultad", Carrera.class);
            consultaCarreras.setParameter("idFacultad", id);

            // Obtener la lista de carreras asociadas
            List<Carrera> carreras = consultaCarreras.getResultList();

            // Imprimir las carreras asociadas a la facultad
            if (carreras.isEmpty()) {
                System.out.println("No se encontraron carreras para la facultad especificada.");
            } else {
                System.out.println("Carreras asociadas a la facultad:");
                for (Carrera carrera : carreras) {
                    System.out.println("Carrera: " + carrera.getNombre() + ", Tipo: " + carrera.getTipo());
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
