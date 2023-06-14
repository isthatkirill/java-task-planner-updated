package isthatkirill.tasklist.service.impl;

import isthatkirill.tasklist.domain.exception.EntityNotFoundException;
import isthatkirill.tasklist.domain.task.Status;
import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.repository.TaskRepository;
import isthatkirill.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public Task getById(Long id) {
        return checkIfExists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task update(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(Status.NEW);
        }
        taskRepository.update(task);
        return task;
    }

    @Override
    @Transactional
    public Task create(Task task, Long userId) {
        if (task.getStatus() == null) {
            task.setStatus(Status.NEW);
        }
        taskRepository.create(task);
        taskRepository.assignToUser(task.getId(), userId);
        return task;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.delete(id);
    }

    private Task checkIfExists(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found", String.valueOf(id)));
    }

}
