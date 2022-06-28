package basic.main;

import basic.entity.UserEntity;
import basic.factory.StaticEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class StaticWithMain {

    public static void main(String[] args) {

        StaticEntityManagerFactory.initialization();

        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();

            UserEntity nextUser = new UserEntity("yunakim@gmail.com", "yunakim", LocalDateTime.now(), LocalDateTime.now());

            /*
             EntityManager 의 persist 는 Persistence Context 에 관리대상으로 지정할 뿐,
             실제 Database 에 접근하는 것은 아님
             */
            entityManager.persist(nextUser);

            /*
            Transaction 을 통해서만 실제 Database 에 접근하게 됨
            단, Read 의 경우
             */
            entityTransaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            entityTransaction.rollback();
        }

        finally {
            entityManager.close();
            StaticEntityManagerFactory.close();
        }



    }
}
