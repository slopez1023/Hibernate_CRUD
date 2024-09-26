
import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Listar todas las facultades
            List<Facultad> facultades = em.createQuery("SELECT f FROM Facultad f", Facultad.class).getResultList();
            System.out.println("Lista de Facultades:");
            facultades.forEach(facultad -> {
                System.out.println(facultad);
            });

            // Listar todas las carreras
            List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
            System.out.println("\nLista de Carreras:");
            carreras.forEach(carrera -> {
                System.out.println("Carrera: " + carrera.getNombre() +
                        ", Tipo: " + carrera.getTipo() +
                        " , Facultad: " + carrera.getFacultad().getNombre());
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
