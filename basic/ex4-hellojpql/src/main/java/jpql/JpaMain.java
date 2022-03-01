package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            System.out.println("===========================");

            Team team = new Team();
            team.setName("Team1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========================");

            List<Member> resultList = em.createQuery("select m from Member m join Team t on m.username = 'member1'", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());

            for (Member resultMember : resultList) {
                System.out.println("resultMember.getTeam().getClass() = " + resultMember.getTeam().getClass());
                System.out.println("resultMember = " + resultMember);
                System.out.println("resultMember.getTeam().getName() = " + resultMember.getTeam().getName());
            }

            System.out.println("===========================");
            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
