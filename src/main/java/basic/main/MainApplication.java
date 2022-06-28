package basic.main;

import basic.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class MainApplication {

    public static void main(String[] args){

        /*
            EntityManager 를 생성하기 위한 Factory Interface
            persistence 단위별로 팩토리를 생성
            persistence-unit 의 name 을 작성해주어야 함

            Persistence class 의 createEntityManagerFactory 는
            내부적으로 PersistenceProvider 를 통해
            persistence-unit 별로 EntityManagerFactory 를 생성해줌을 알 수 있음
            (Persistence class 참조)
         */
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("basicjpa");

        /*
        EntityManager interface 는 Hibernate SessionImpl class 를 통해 구현됨
        EntityManager 는 Persistence Context 와 Entity 를 관리
        하나의 Connection 이나 Session 으로 봐도 무방
         */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // EntityManager 로부터 Transaction 을 가져와 관리할 인스턴스 생성
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();

            UserEntity newUser = new UserEntity("lullaby2537@naver.com", "back"
                    , LocalDateTime.now() ,LocalDateTime.now());

            /*
            newUser 의 상태를 Persistence Context 에 추가하고 관리대상으로 반영
            Transaction 에 Commit 하기 전까지는 쿼리문이 실행되지 않음
            Persistence Context 와 DB 반영은 별개임을 알 수 있음
            */
            entityManager.persist(newUser);

            //실제 JPA 가 쿼리를 생성하여 DB 에 커밋
            entityTransaction.commit();

        } catch (Exception e){
            e.printStackTrace();

            //Exception 발생 시 Rollback
            entityTransaction.rollback();
            System.out.println("Transaction Rollback");
        } finally {

            //EntityManager 종료. JDBC 에서 Connection 을 종료하는 것과 동일한 기능으로 봐도 됨
            entityManager.close();
        }

        //Factory 객체 소멸. Connection Pool 자원 등을 반환(Debugging 으로 확인할 수 있음)
        entityManagerFactory.close();
    }
}
