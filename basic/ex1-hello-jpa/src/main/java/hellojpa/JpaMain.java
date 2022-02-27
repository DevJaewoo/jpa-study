package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            Member member2 = new Member();
            member2.setName("B");
            member2.setTeam(team);

            Member member3 = new Member();
            member3.setName("C");
            member3.setTeam(team);


            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            System.out.println("=================");

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            for (Member member : members) {
                System.out.println("member.getName() = " + member.getName());
            }

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
