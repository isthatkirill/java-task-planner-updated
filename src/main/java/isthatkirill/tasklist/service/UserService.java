package isthatkirill.tasklist.service;

import isthatkirill.tasklist.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User update(User user);

    User create(User user);

    boolean isOwner(Long userId, Long taskId);

    void delete(Long id);

}
