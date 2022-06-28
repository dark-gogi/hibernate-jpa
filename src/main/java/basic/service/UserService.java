package basic.service;

import basic.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(UserEntity userEntity);

    Optional<UserEntity> getUser(String email);

    void updateUserName(String email, String changedName);

    List<UserEntity> getUserList();

    void deleteUser(String email);
}
