package cybuy.item;

import cybuy.user.UserEntity;
import cybuy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//starter data rest
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItems() {

        List<ItemEntity> items = new ArrayList<>(itemRepository.findAll());
        return items.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemNum}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable("itemNum") long itemNum) {

        Optional<ItemEntity> itemEntityData = itemRepository.findById(itemNum);
        return itemEntityData.map(itemEntity -> new ResponseEntity<>(itemEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ItemEntity> createItem(@PathVariable("userId") long userId , @RequestBody ItemEntity itemEntity) {

//        try {
//
//            ItemEntity _ItemEntity = itemRepository.save(new ItemEntity(itemEntity.getTitle(), itemEntity.getDescription(), itemEntity.getPrice()));
//            return new ResponseEntity<>(_ItemEntity, HttpStatus.CREATED);
//        } catch (Exception e) {
//
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return userRepository.findById(userId).map(user -> {

            itemEntity.setUser_entity(user);
            return new ResponseEntity<>(itemRepository.save(itemEntity), HttpStatus.CREATED);
        }).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping("/{itemNum}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable("itemNum") long itemNum, @RequestBody ItemEntity itemEntity) {

        Optional<ItemEntity> itemEntityData = itemRepository.findById(itemNum);
        if(itemEntityData.isPresent()) {

            ItemEntity _itemEntity = itemEntityData.get();
            _itemEntity.setTitle(itemEntity.getTitle());
            _itemEntity.setDescription(itemEntity.getDescription());
            _itemEntity.setPrice(itemEntity.getPrice());
            return new ResponseEntity<>(itemRepository.save(_itemEntity), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{itemNum}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("itemNum") long itemNum) {

        try {

            itemRepository.deleteById(itemNum);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/title/{part}")
    public ResponseEntity<List<ItemEntity>> getItemByTitleContaining(@PathVariable("part") String part) {

        try {

            List<ItemEntity> items = itemRepository.findByTitleContainingIgnoreCase(part);

            return items.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matchingUser/{itemNum}")
    public ResponseEntity<UserEntity> getMatchingUser(@PathVariable("itemNum") long itemNum) {

        UserEntity userEntity = userRepository.findByItemsContaining(itemNum);
        return userEntity == null ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
}
