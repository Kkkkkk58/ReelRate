package ru.kslacker.reelrate.dataaccess.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.PersonRoleType;

@Entity
@Table(name = "actor_roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActingPersonRole extends PersonRole {

    @Basic(optional = false)
    private String role;

    public ActingPersonRole(MotionPicture motionPicture, Person person, String role) {
        super(PersonRoleType.ACTOR, motionPicture, person);
        this.role = role;
    }
}
