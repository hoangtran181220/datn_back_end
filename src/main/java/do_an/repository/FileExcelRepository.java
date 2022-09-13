package do_an.repository;

import do_an.entity.FileExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileExcelRepository extends JpaRepository<FileExcelEntity,Long> {
}
