package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("=================");

            Member member = new Member();
            member.setName("WowHelloJPA");

            em.persist(member);

//            em.flush();
//            em.clear();

            System.out.println("=================");

            Member findMember = em.createQuery("select m from Member m where m.name like '%Hello%'", Member.class).getResultStream().findAny().orElse(null);
            System.out.println("findMember.getName() = " + findMember.getName());

            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();
    }
}
