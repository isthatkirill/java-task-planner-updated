package isthatkirill.tasklist.repository.mapper;

import isthatkirill.tasklist.domain.task.Status;
import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.domain.user.Role;
import isthatkirill.tasklist.domain.user.User;
import org.springframework.jdbc.core.RowMapper;

public class EntityRowMapper {

    public static final RowMapper<Task> TASK_MAPPER = ((rs, rowNum) ->
            Task.builder()
                    .id(rs.getLong("id"))
                    .description(rs.getString("description"))
                    .title(rs.getString("title"))
                    .status(Status.valueOf(rs.getString("status")))
                    .expirationDate(rs.getTimestamp("expiration_date").toLocalDateTime())
                    .build());

    public static final RowMapper<User> USER_MAPPER = ((rs, rowNum) ->
            User.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .build());

    public static final RowMapper<Role> ROLE_MAPPER = ((rs, rowNum) -> Role.valueOf(rs.getString("role")));

}
