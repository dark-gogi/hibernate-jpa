package basic.service.impl;

import basic.entity.UserEntity;
import basic.exception.DuplicateUserException;
import basic.exception.NotFoundException;
import basic.factory.StaticEntityManagerFactory;
import basic.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    /*
    EntityManager 는 EntityManagerFactory 를 통해 요청이 올 때마다 Thread 별로 생성됨
    즉, Thread-safe 하게 사용되어야 함
     */

    @Override
    public void saveUser(UserEntity userEntity) {

        StaticEntityManagerFactory.initialization();
        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            UserEntity foundUser = entityManager.find(UserEntity.class,userEntity.getEmail());

            if(foundUser!=null){
                throw new DuplicateUserException();
            }

            entityManager.persist(foundUser);

            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<UserEntity> getUser(String email) {

        StaticEntityManagerFactory.initialization();
        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();

        UserEntity userEntity = entityManager.find(UserEntity.class, email);

        entityManager.persist(userEntity);

        entityManager.close();

        return Optional.ofNullable(userEntity);
    }

    @Override
    public void updateUserName(String email, String changedName) {

        StaticEntityManagerFactory.initialization();
        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            UserEntity userEntity = entityManager.find(UserEntity.class,email);

            userEntity.setName(changedName);

            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    @Override
    public List<UserEntity> getUserList() {

        StaticEntityManagerFactory.initialization();
        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();

        try{

            TypedQuery<UserEntity> typedQuery = entityManager.createQuery("select u from UserEntity as u", UserEntity.class);

            return typedQuery.getResultList();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

            return null;
    }

    @Override
    public void deleteUser(String email) {

        StaticEntityManagerFactory.initialization();

        EntityManager entityManager = StaticEntityManagerFactory.createEntityManager();

        try{
            entityManager.getTransaction().begin();

            UserEntity userEntity = entityManager.find(UserEntity.class,email);

            if(userEntity==null)
                throw new NotFoundException();

            entityManager.remove(userEntity);

            entityManager.getTransaction().commit();
        } catch(Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
