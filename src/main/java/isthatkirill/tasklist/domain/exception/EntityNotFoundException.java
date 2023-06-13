package isthatkirill.tasklist.domain.exception;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private String message;
    private String entityField;

}
