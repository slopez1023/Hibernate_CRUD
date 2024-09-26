

import entidades.Carrera;
import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import javax.swing.*;

public class HibernateCrearCarrera {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            // Solicitar los datos para crear la nueva carrera
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la carrera");
            String tipo = JOptionPane.showInputDialog("Ingrese el tipo de la carrera");
            Long idFacultad = Long.valueOf(JOptionPane.showInputDialog("Ingrese el ID de la facultad"));

            // Buscar la facultad correspondiente
            Facultad facultad = em.find(Facultad.class, idFacultad);
            if (facultad == null) {
                System.out.println("Facultad no encontrada.");
                return;
            }

            // Iniciar la transacción para crear la carrera
            em.getTransaction().begin();
            Carrera carrera = new Carrera();
            carrera.setNombre(nombre);
            carrera.setTipo(tipo);
            carrera.setFacultad(facultad);
            em.persist(carrera);
            em.getTransaction().commit();

            System.out.println("Carrera creada con éxito: " + carrera);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
