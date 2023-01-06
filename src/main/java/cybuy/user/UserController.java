package cybuy.user;

import cybuy.user.auth.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {

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
    public ResponseEntity<Long> createUser(@RequestBody UserEntity userEntity) {

        long id = userService.saveUser(userEntity);
        return id == -1 ? new ResponseEntity<>(id, HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(id, HttpStatus.OK);
    }
}
