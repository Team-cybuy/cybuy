package cybuy.user;

import cybuy.item.ItemEntity;
import cybuy.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllItems() {

        List<UserEntity> users = new ArrayList<>(userRepository.findAll());
        return users.isEmpty() ? new ResponseEntity<>(null, HttpStatus.NO_CONTENT) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") long userId) {

        Optional<UserEntity> userEntityData = userRepository.findById(userId);
        return userEntityData.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {

        try {

            UserEntity _UserEntity = userRepository.save(new UserEntity(userEntity.getMail(), userEntity.getFirst_name(),
                    userEntity.getLast_name(), userEntity.getAddress(), userEntity.getPostal_code()));
            return new ResponseEntity<>(_UserEntity, HttpStatus.CREATED);
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
