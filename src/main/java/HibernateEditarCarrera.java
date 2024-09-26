
import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import javax.swing.*;

public class HibernateEditarCarrera {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Solicitar el ID de la carrera a modificar
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el ID de la carrera a modificar"));
            Carrera carrera = em.find(Carrera.class, id);

            if (carrera == null) {
                System.out.println("Carrera no encontrada.");
                return;
            }

            // Solicitar los nuevos datos
            String nuevoNombre = JOptionPane.showInputDialog("Ingrese nuevo nombre de la carrera", carrera.getNombre());
            String nuevoTipo = JOptionPane.showInputDialog("Ingrese el nuevo tipo de la carrera", carrera.getTipo());
            Long idFacultad = Long.valueOf(JOptionPane.showInputDialog("Ingrese el ID de la facultad", carrera.getFacultad().getId()));

            // Buscar la nueva facultad
            Facultad nuevaFacultad = em.find(Facultad.class, idFacultad);
            if (nuevaFacultad == null) {
                System.out.println("Facultad no encontrada.");
                return;
            }

            // Iniciar la transacción y actualizar la carrera
            em.getTransaction().begin();
            carrera.setNombre(nuevoNombre);
            carrera.setTipo(nuevoTipo);
            carrera.setFacultad(nuevaFacultad);
            em.merge(carrera);
            em.getTransaction().commit();

            System.out.println("Carrera actualizada: " + carrera);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
