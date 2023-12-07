package ru.kslacker.reelrate.dataaccess.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

public class MotionPictureSpecifications {
    public static Specification<MotionPicture> withType(MotionPictureType type) {
        if (type == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }

    public static Specification<MotionPicture> withTitle(String title) {
        if (title == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title));
    }

    public static Specification<MotionPicture> withGenre(Genre genre) {
        if (genre == null) {
            return null;
        }

        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("genres")));
    }
}
