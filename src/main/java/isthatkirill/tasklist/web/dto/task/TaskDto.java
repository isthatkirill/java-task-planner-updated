package isthatkirill.tasklist.web.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import isthatkirill.tasklist.domain.task.Status;
import isthatkirill.tasklist.web.dto.validation.OnCreate;
import isthatkirill.tasklist.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    @NotNull(message = "Id cannot be null", groups = OnUpdate.class)
    private Long id;

    @Length(
            max = 255,
            message = "Title length must be smaller than 255 symbols",
            groups = {OnUpdate.class, OnCreate.class}
    )
    @NotNull(message = "Title cannot be null", groups = {OnUpdate.class, OnCreate.class})
    private String title;

    @Length(
            max = 255,
            message = "Description length must be smaller than 255 symbols",
            groups = {OnUpdate.class, OnCreate.class}
    )
    private String description;

    private Status status;

    @NotNull(message = "Expiration date cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;

}
