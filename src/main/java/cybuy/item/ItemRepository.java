package cybuy.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    @Transactional
    List<ItemEntity> findByTitleContainingIgnoreCase(String part);
}
