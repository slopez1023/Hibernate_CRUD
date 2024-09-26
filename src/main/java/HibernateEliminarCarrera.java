import entidades.Carrera;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.Scanner;

public class HibernateEliminarCarrera {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Digite el ID de la carrera a eliminar: ");
        Long id = s.nextLong();

        EntityManager em = JpaUtil.getEntityManager();

        try {
            Carrera carrera = em.find(Carrera.class, id);

            if (carrera == null) {
                System.out.println("Carrera no encontrada.");
                return;
            }

            em.getTransaction().begin();
            em.remove(carrera);
            em.getTransaction().commit();

            System.out.println("Carrera eliminada con éxito.");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}