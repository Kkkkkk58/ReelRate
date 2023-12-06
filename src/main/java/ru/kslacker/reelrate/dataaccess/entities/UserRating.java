package ru.kslacker.reelrate.dataaccess.entities;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dataaccess.models.UserRatingId;

@Entity
@Table(name = "user_ratings")
@IdClass(UserRatingId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRating {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Id
    @Column(name = "motion_picture_id", nullable = false)
    private Long motionPictureId;

    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private ReelRateUser user;

    @JoinColumn(
            name = "motion_picture_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private MotionPicture motionPicture;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "rating"))
    private Rating rating;

    public UserRating(UUID userId, Long motionPictureId, Rating rating) {
        this.userId = userId;
        this.motionPictureId = motionPictureId;
        this.rating = rating;
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
        UserRating that = (UserRating) o;
        return getUserId() != null && Objects.equals(getUserId(), that.getUserId())
                && getMotionPictureId() != null && Objects.equals(getMotionPictureId(), that.getMotionPictureId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(userId, motionPictureId);
    }
}
