package ru.kslacker.reelrate.dataaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ratings_view")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MotionPictureRatingView {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "motion_picture_id", nullable = false)
    private Long motionPictureId;

    @OneToOne(optional = false)
    @JoinColumn(
            name = "motion_picture_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private MotionPicture motionPicture;
    private Double averageScore;
    private Integer numberOfVotes;

    public MotionPictureRatingView(
            Long motionPictureId,
            Double averageScore,
            Integer numberOfVotes
    ) {
        this.motionPictureId = motionPictureId;
        this.averageScore = averageScore;
        this.numberOfVotes = numberOfVotes;
    }

    public MotionPictureRatingView(
            MotionPicture motionPicture,
            Double averageScore,
            Integer numberOfVotes
    ) {
        this(motionPicture.getId(), averageScore, numberOfVotes);
        this.motionPicture = motionPicture;
    }
}
