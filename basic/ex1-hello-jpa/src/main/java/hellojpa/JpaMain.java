package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
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
            member.setName("member");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
            member.getAddressHistory().add(new Address("old2", "street", "zipcode"));

            em.persist(member);
            
            em.flush();
            em.clear();
            
            System.out.println("=================");

            Member findMember = em.find(Member.class, member.getId());

            Address homeAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("과자");

            List<Address> addressHistory = findMember.getAddressHistory();
            Address address = addressHistory.stream().filter(addr -> addr.getCity().equals("old1")).findAny().get();

            addressHistory.remove(address);
            addressHistory.add(new Address("new1", address.getStreet(), address.getZipcode()));

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
