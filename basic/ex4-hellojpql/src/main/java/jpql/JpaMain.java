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
            member.setMemberType(MemberType.ADMIN);
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========================");

            List<Object[]> resultList = em.createQuery("select m.username, 'HELLO', TRUE, m.memberType from Member m where m.memberType = :adminType")
                    .setParameter("adminType", MemberType.ADMIN)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());

            for (Object[] objects : resultList) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
                System.out.println("objects[3] = " + objects[3]);
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
