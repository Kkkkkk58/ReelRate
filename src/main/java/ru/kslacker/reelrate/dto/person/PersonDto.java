package ru.kslacker.reelrate.dto.person;

import java.util.List;

import lombok.Builder;
import ru.kslacker.reelrate.dto.personrole.PersonRoleDto;

@Builder
public record PersonDto(Long id, String name, List<PersonRoleDto> roles) {

}