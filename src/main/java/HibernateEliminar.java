import entidades.Facultad;
import jakarta.persistence.EntityManager;
import utilidades.JpaUtil;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {

        Scanner s =new Scanner(System.in);
        System.out.printf("Digite el código de facultad a Eliminar: ");
        Long id =s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();

        try {

            Facultad facultad =em.find(Facultad.class,id);
            em.getTransaction().begin();
            // remove es el coamndo que borra fisicamente el registro de la tabla
            em.remove(facultad);
            em.getTransaction().commit();

        }catch (Exception e){
            em .getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
