package ru.kslacker.reelrate.dataaccess.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @OneToOne(optional = false)
    private MotionPicture motionPicture;
    private Double averageScore;
    private Integer numberOfVotes;
}
