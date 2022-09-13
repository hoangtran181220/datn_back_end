package do_an.repository;

import do_an.entity.DetailRequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DetailRequestRepository extends JpaRepository<DetailRequestEntity,Long> {
    @Query(value = "select i from DetailRequestEntity i where i.isActive = 1 " +
            "and (:tenKhoanTien is null or i.tenKhoanTien like %:tenKhoanTien%) ")
    Page<DetailRequestEntity> searchDRq(String tenKhoanTien, Pageable pageable);

}
