package ru.kslacker.reelrate.dto.personrole;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.PersonRoleType;

@Getter
@ToString
public class ActingPersonRoleDto extends PersonRoleDto {
    private final String role;

    @Builder
    public ActingPersonRoleDto(
            Long id,
            Long motionPictureId,
            Long personId,
            String role) {

        super(id, PersonRoleType.ACTOR, motionPictureId, personId);
        this.role = role;
    }

    public static class ActingPersonRoleDtoBuilder extends PersonRoleDtoBuilder {
        ActingPersonRoleDtoBuilder() {
            super();
        }
    }
}
