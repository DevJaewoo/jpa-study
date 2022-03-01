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

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            em.persist(member);

            Order order = new Order();
            order.setMember(member);
            order.setAddress(new Address("city", "street", "zipcode"));

            em.persist(order);

            System.out.println("===========================");

            Member result = em.createQuery("select m from Member as m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("result.getUsername() = " + result.getUsername());

            Address result2 = em.createQuery("select o.address from Order as o", Address.class).getSingleResult();
            System.out.println("result2.getCity() = " + result2.getCity());

            MemberDTO result3 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getSingleResult();
            System.out.println("result3.getUsername() = " + result3.getUsername());

            System.out.println("===========================");
            em.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
