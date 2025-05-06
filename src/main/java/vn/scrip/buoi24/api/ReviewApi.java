package vn.scrip.buoi24.api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Review;
import vn.scrip.buoi24.service.ReviewService;

import java.util.List;
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;
    @GetMapping("/{movieId}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Integer movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovieId(movieId));
    }
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(review));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, @RequestBody Review updatedReview) {
        Review review = reviewService.updateReview(id, updatedReview);
        return (review != null) ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        return reviewService.deleteReview(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
