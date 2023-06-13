package isthatkirill.tasklist.repository.impl;

import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.repository.TaskRepository;
import isthatkirill.tasklist.repository.mapper.EntityRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_ID = """
            SELECT *
            FROM tasks
            WHERE id = ?""";

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.*
            FROM tasks as t
            LEFT JOIN users_tasks as ut on t.id = ut.task_id
            WHERE ut.user_id = ?""";

    private final String ASSIGN_TASK = """
            INSERT INTO users_tasks (user_id, task_id)
            VALUES (?, ?)""";

    private final String DELETE = """
            DELETE FROM tasks
            WHERE id = ?""";

    private final String UPDATE = """
            UPDATE tasks
            SET title = ?,
            description = ?,
            expiration_date = ?,
            status = ?
            WHERE id = ?""";

    private final String CREATE = """
            INSERT INTO tasks (title, description, expiration_date, status)
            VALUES(?, ?, ?, ?)""";

    @Override
    public Optional<Task> findById(Long id) {
        List<Task> tasks = jdbcTemplate.query(FIND_BY_ID, EntityRowMapper.TASK_MAPPER, id);
        if (tasks.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(tasks.get(0));
    }

    @Override
    public List<Task> findAllByUserId(Long userId) {
        return jdbcTemplate.query(FIND_ALL_BY_USER_ID, EntityRowMapper.TASK_MAPPER, userId);
    }

    @Override
    public void assignToUser(Long taskId, Long userId) {
        jdbcTemplate.query(ASSIGN_TASK, EntityRowMapper.TASK_MAPPER, userId, taskId);

    }

    @Override
    public void update(Task task) {
        jdbcTemplate.update(UPDATE,
                task.getTitle(),
                task.getDescription(),
                Timestamp.valueOf(task.getExpirationDate()),
                task.getStatus().toString(),
                task.getId());
    }

    @Override
    public void create(Task task) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", task.getTitle());
        parameters.put("description", task.getDescription());
        parameters.put("status", task.getStatus().toString());
        parameters.put("expiration_date", Timestamp.valueOf(task.getExpirationDate()));

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        task.setId(id.longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

}
