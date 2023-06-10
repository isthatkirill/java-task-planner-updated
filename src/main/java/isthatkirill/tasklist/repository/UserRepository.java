package isthatkirill.tasklist.repository;

import isthatkirill.tasklist.domain.user.Role;
import isthatkirill.tasklist.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void update(User user);

    void create(User user);

    void insertUserRole(Long id, Role role);

    boolean isOwner(Long userId, Long taskId);

    void delete(Long id);
}
