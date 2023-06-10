package isthatkirill.tasklist.web.mapper;

import isthatkirill.tasklist.domain.user.User;
import isthatkirill.tasklist.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toUser(UserDto userDto);

}
