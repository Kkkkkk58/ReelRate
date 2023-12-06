package ru.kslacker.reelrate.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.kslacker.reelrate.service.api.RatingViewUpdatingService;

@Slf4j
@Component
public class MotionPictureRatingViewUpdatingJob {
    private final RatingViewUpdatingService ratingUpdatingService;

    public MotionPictureRatingViewUpdatingJob(RatingViewUpdatingService ratingUpdatingService) {
        this.ratingUpdatingService = ratingUpdatingService;
    }

    @Scheduled(cron = "${reelrate.job.rating-update.cron:@daily}")
    @Async
    public void updateMotionPictureRatingStats() {
        log.info("Started rating update job");
        ratingUpdatingService.updateRatingView();
        log.info("Ratings are updated successfully");
    }
}
