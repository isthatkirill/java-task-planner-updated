package isthatkirill.tasklist.repository.impl;

import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.domain.user.Role;
import isthatkirill.tasklist.domain.user.User;
import isthatkirill.tasklist.repository.UserRepository;
import isthatkirill.tasklist.repository.mapper.EntityRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_ID = """
            SELECT *
            FROM users
            WHERE id = ?""";

    private final String FIND_BY_USERNAME = """
            SELECT *
            FROM users
            WHERE username = ?""";

    private final String UPDATE = """
            UPDATE users
            SET name = ?,
                username = ?,
                password = ?
            WHERE id = ?""";

    private final String CREATE = """
            INSERT INTO users (name, username, password)
            VALUES (?, ?, ?)""";

    private final String INSERT_USER_ROLE = """
            INSERT INTO users_roles (user_id, role) 
            VALUES (?, ?)""";

    private final String DELETE = """
            DELETE FROM users
            WHERE id = ?""";

    private final String IS_OWNER = """
            SELECT exists(
                SELECT 1
                FROM users_tasks
                WHERE user_id = ?
                AND task_id = ?
                       )""";

    private final String FIND_ROLES = """
            SELECT ur.role
            FROM users_roles ur
            LEFT JOIN users u on ur.user_id = u.id
            WHERE u.id = ?""";

    private final String FIND_TASKS = """
            SELECT t.*
            FROM tasks as t
            LEFT JOIN users_tasks as ut on t.id = ut.task_id
            WHERE ut.user_id = ?""";



    @Override
    public Optional<User> findById(Long id) {
        List<User> users = jdbcTemplate.query(FIND_BY_ID, EntityRowMapper.USER_MAPPER, id);
        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(setRolesAndTasks(users.get(0)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = jdbcTemplate.query(FIND_BY_USERNAME, EntityRowMapper.USER_MAPPER, username);
        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(setRolesAndTasks(users.get(0)));
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(UPDATE,
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getId());
    }

    @Override
    public void create(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());

        Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        user.setId(id.longValue());
    }

    @Override
    public void insertUserRole(Long id, Role role) {
        jdbcTemplate.update(INSERT_USER_ROLE, id, role.name());
    }

    @Override
    public boolean isOwner(Long userId, Long taskId) {
        return jdbcTemplate.query(IS_OWNER, (rs, rowNum) ->
                rs.getBoolean(1),userId, taskId).get(0);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    private User setRolesAndTasks(User user) {
        List<Role> roles = jdbcTemplate
                .query(FIND_ROLES, EntityRowMapper.ROLE_MAPPER, user.getId());
        user.setRoles(new HashSet<>(roles));
        List<Task> tasks = jdbcTemplate.query(FIND_TASKS, EntityRowMapper.TASK_MAPPER, user.getId());
        user.setTasks(tasks);
        return user;
    }
}
