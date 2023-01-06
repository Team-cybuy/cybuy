package cybuy.user;

import cybuy.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    UserEntity findByMailIgnoreCase(String mail);

    boolean existsByMailIgnoreCase(String mail);

    @Transactional
    @Query("from UserEntity user join user.items items where items.item_number = ?1")
    UserEntity findByItemsContaining(long itemNumber);
}
