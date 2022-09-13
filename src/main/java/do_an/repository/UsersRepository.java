package do_an.repository;

import do_an.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String username);
}
