package ru.kslacker.reelrate.dto.personrole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.PersonRoleType;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class PersonRoleDto {
    private final Long id;
    private final PersonRoleType type;
    private final Long motionPictureId;
    private final Long personId;
}
