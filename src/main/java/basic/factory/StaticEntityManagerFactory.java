package basic.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
    EntityManagerFactory, EntityManager, EntityTransaction 등은
    Java Persistence API 에 명세된 Java ORM 표준 interface
    Hibernate 는 EntityManagerFactory 를 SessionFactoryImpl class 로 구현
    EntityManagerFactory 는 Persistence 단위로 생성되므로, 공유하여 사용해야 할 경우가 많음
 */
public class StaticEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;

    public static void initialization(){
        if(entityManagerFactory == null){

            //JPA 가 Hibernate 의 SessionFactoryImpl 인스턴스를 Persistence 단위로 생성하여 반환
            entityManagerFactory = Persistence.createEntityManagerFactory("basicjpa");
        }
    }

    public static EntityManager createEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(){
        entityManagerFactory.close();
    }


}
