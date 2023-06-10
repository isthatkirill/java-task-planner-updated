package isthatkirill.tasklist.web.controller;

import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.service.TaskService;
import isthatkirill.tasklist.web.dto.task.TaskDto;
import isthatkirill.tasklist.web.dto.validation.OnUpdate;
import isthatkirill.tasklist.web.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        return taskMapper.toDto(task);
    }

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        Task updated = taskService.update(task);
        return taskMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

}
