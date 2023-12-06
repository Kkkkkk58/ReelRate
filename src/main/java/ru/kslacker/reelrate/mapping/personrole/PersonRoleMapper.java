package ru.kslacker.reelrate.mapping.personrole;

import ru.kslacker.reelrate.dataaccess.entities.ActingPersonRole;
import ru.kslacker.reelrate.dataaccess.entities.PersonRole;
import ru.kslacker.reelrate.dto.personrole.ActingPersonRoleDto;
import ru.kslacker.reelrate.dto.personrole.PersonRoleDto;

public class PersonRoleMapper {
    public static PersonRoleDto map(PersonRole personRole) {
        return PersonRoleDto.builder()
                .id(personRole.getId())
                .motionPictureId(personRole.getMotionPicture().getId())
                .personId(personRole.getPerson().getId())
                .type(personRole.getType())
                .build();
    }

    public static ActingPersonRoleDto map(ActingPersonRole actingPersonRole) {
        return ActingPersonRoleDto.builder()
                .role(actingPersonRole.getRole())
                .id(actingPersonRole.getId())
                .motionPictureId(actingPersonRole.getMotionPicture().getId())
                .personId(actingPersonRole.getPerson().getId())
                .build();
    }
}
