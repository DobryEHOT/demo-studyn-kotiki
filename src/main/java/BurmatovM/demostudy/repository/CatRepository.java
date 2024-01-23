package BurmatovM.demostudy.repository;

import BurmatovM.demostudy.entity.CatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CatRepository extends JpaRepository<CatEntity, Long>{
}
