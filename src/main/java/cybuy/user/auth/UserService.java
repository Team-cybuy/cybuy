package cybuy.user.auth;

import cybuy.user.UserEntity;
import cybuy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService implements IUserService, UserDetailsService {

    private Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+[.][a-zA-Z.]+$"); // RFC 5322 (modified)

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public long saveUser(UserEntity user) {

        String mail = user.getMail();
        if (!mailPattern.matcher(mail).find()) return -1;
        if (userRepository.existsByMailIgnoreCase(mail)) return -1;

        UserEntity _user = new UserEntity();
        _user.setMail(user.getMail());
        _user.setFirst_name(user.getFirst_name());
        _user.setLast_name(user.getLast_name());
        _user.setAddress(user.getAddress());
        _user.setPostal_code(user.getPostal_code());
        _user.setPassword(passwordEncoder.encode(user.getPassword()));
        _user.setRole("USER");

        _user = userRepository.save(_user);
        return _user.getUser_id();
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByMailIgnoreCase(mail);
        if(user == null) {

            throw new UsernameNotFoundException("Username not found");
        }

        return new User(
                user.getMail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
