package isthatkirill.tasklist.web.security;

import isthatkirill.tasklist.domain.user.Role;
import isthatkirill.tasklist.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
