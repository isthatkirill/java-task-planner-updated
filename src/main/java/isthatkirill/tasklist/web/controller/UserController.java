package isthatkirill.tasklist.web.controller;

import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.domain.user.User;
import isthatkirill.tasklist.service.TaskService;
import isthatkirill.tasklist.service.UserService;
import isthatkirill.tasklist.web.dto.task.TaskDto;
import isthatkirill.tasklist.web.dto.user.UserDto;
import isthatkirill.tasklist.web.dto.validation.OnCreate;
import isthatkirill.tasklist.web.dto.validation.OnUpdate;
import isthatkirill.tasklist.web.mapper.TaskMapper;
import isthatkirill.tasklist.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id,
                              @Validated(OnCreate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        Task created = taskService.create(task, id);
        return taskMapper.toDto(created);
    }


    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User updated = userService.update(user);
        return userMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
