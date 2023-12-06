package ru.kslacker.reelrate.dataaccess.visitors;

import ru.kslacker.reelrate.dataaccess.entities.Cartoon;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.entities.Series;

public interface MotionPictureVisitor {
    void visit(MotionPicture motionPicture);
    void visit(Movie movie);
    void visit(Series series);
    void visit(Cartoon cartoon);
}
