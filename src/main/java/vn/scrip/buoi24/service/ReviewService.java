package vn.scrip.buoi24.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.Review;
import vn.scrip.buoi24.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    // ✅ Lấy danh sách review theo movieId
    public List<Review> getReviewsByMovieId(Integer movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    // ✅ Thêm review mới
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    // ✅ Tạo review mới (thêm phương thức này)
    public Review createReview(Review review) {
        return addReview(review);
    }

    // ✅ Cập nhật review
    public Review updateReview(Integer id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setContent(updatedReview.getContent());
            review.setRating(updatedReview.getRating());
            return reviewRepository.save(review);
        }
        return null; // hoặc throw Exception
    }

    // ✅ Xóa review theo id
    public boolean deleteReview(Integer id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
