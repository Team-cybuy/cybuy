package cybuy.user.auth;

import cybuy.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    public long saveUser(UserEntity user);
}
