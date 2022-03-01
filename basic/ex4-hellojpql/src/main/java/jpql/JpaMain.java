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

//            String query = "select " +
//                    "case when m.age <= 10 then '학생요굼' " +
//                    "when m.age >= 60 then '경로요금' " +
//                    "else '일반요금' " +
//                    "end " +
//                    "from Member m";

//            String query = "select coalesce(m.username, 'Anonymous') from Member m";

            String query = "select nullif(m.username, 'member1') from Member m";

            List<String> resultList = em.createQuery(query, String.class).getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
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
