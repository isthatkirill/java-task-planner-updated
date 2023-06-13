package isthatkirill.tasklist.repository.mapper;

import isthatkirill.tasklist.domain.task.Status;
import isthatkirill.tasklist.domain.task.Task;
import org.springframework.jdbc.core.RowMapper;

public class TaskRowMapper {

    public static final RowMapper<Task> TASK_MAPPER = ((rs, rowNum) ->
            Task.builder()
                    .id(rs.getLong("task_id"))
                    .description(rs.getString("description"))
                    .title(rs.getString("title"))
                    .status(Status.valueOf(rs.getString("status")))
                    .expirationDate(rs.getTimestamp("expiration_date").toLocalDateTime())
                    .build());

}
