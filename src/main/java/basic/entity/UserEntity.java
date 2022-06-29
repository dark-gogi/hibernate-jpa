package basic.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public UserEntity(String email, String name, LocalDateTime createAt, LocalDateTime updateAt) {
        this.email = email;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public UserEntity(){
    }

    public void changeName(String changedName){
        this.name = changedName;
        updateDateTime();
    }

    public void updateDateTime(){
        this.updateAt = LocalDateTime.now();
    }
}
