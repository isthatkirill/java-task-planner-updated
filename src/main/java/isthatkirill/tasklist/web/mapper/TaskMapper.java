package isthatkirill.tasklist.web.mapper;

import isthatkirill.tasklist.domain.task.Task;
import isthatkirill.tasklist.web.dto.task.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    List<TaskDto> toDto(List<Task> tasks);

    Task toTask(TaskDto taskDto);

}
