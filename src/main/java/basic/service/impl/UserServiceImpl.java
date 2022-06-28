package basic.service.impl;

import basic.entity.UserEntity;
import basic.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    /*
    EntityManager 는 EntityManagerFactory 를 통해 요청이 올 때마다 Thread 별로 생성됨
    즉, Thread-safe 하게 사용되어야 함
     */

    @Override
    public void saveUser(UserEntity userEntity) {

    }

    @Override
    public Optional<UserEntity> getUser(String email) {
        return Optional.empty();
    }

    @Override
    public void updateUserName(String email, String changedName) {

    }

    @Override
    public List<UserEntity> getUserList() {
        return null;
    }

    @Override
    public void deleteUser(String email) {

    }
}
