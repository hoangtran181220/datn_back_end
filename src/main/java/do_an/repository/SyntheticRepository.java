package do_an.repository;

import do_an.entity.SyntheticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyntheticRepository extends JpaRepository<SyntheticEntity,Long> {
}
