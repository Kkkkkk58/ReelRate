package ru.kslacker.reelrate.mapping.person;

import ru.kslacker.reelrate.dataaccess.entities.Person;
import ru.kslacker.reelrate.dto.person.PersonDto;
import ru.kslacker.reelrate.mapping.personrole.PersonRoleMapper;

public class PersonMapper {
    public static PersonDto map(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .roles(person.getRoles().stream().map(PersonRoleMapper::map).toList())
                .build();
    }
}
