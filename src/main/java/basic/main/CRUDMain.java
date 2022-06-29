package basic.main;

import basic.factory.StaticEntityManagerFactory;
import basic.service.UserService;
import basic.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CRUDMain {

    public static void main(String[] args) throws IOException {

        StaticEntityManagerFactory.initialization();
        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        UserService userService = new UserServiceImpl();



        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }
}
