package isthatkirill.tasklist.domain.user;

import isthatkirill.tasklist.domain.task.Task;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;
    private List<Task> tasks;

}
