package ru.kslacker.reelrate.dataaccess.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

@Entity
@Table(name = "motion_pictures")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class MotionPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(insertable=false, updatable=false)
    private MotionPictureType type;
    private String title;
    private String description;
    private Set<Genre> genres = new HashSet<>();
    @OneToMany(mappedBy = "motionPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<PersonRole> creators = new HashSet<>();

    @OneToMany(mappedBy = "motionPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<ActingPersonRole> cast = new HashSet<>();
    private Integer durationMinutes;
    private LocalDate releaseDate;

    protected MotionPicture(
            MotionPictureType type,
            String title,
            String description,
            Set<Genre> genres,
            Set<PersonRole> creators,
            Set<ActingPersonRole> cast,
            Integer durationMinutes,
            LocalDate releaseDate
    ) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.creators = creators;
        this.cast = cast;
        this.durationMinutes = durationMinutes;
        this.releaseDate = releaseDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        MotionPicture that = (MotionPicture) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
    }
}
