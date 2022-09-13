package do_an.repository;

import do_an.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<do_an.entity.RequestEntity,Long> {
    @Query(value = "select i from RequestEntity i where i.isActive = 1 " +
            "and (:code is null or i.code like %:code%) " +
            "and (:title is null or i.title like %:title%) " +
            "and (:nameNvYc is null or i.nameNvYc like %:nameNvYc%)")
    Page<RequestEntity> searchRq(String code, String title, String nameNvYc, Pageable pageable);

    RequestEntity findByCodeAndIsActive(String code, Integer isActive);
}
