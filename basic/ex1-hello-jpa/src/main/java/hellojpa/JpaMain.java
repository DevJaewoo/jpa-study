package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("=================");

            Team team = new Team();
            team.setName("TEAM1");
            em.persist(team);

            System.out.println("=================");

            Member member1 = new Member();
            member1.setName("A");
            member1.setTeam(team);
            em.persist(member1);

            em.flush();
            em.clear();

            System.out.println("=================");

            Member findMember = em.find(Member.class, member1.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("findTeam = " + findTeam.getName());

            System.out.println("=================");

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
