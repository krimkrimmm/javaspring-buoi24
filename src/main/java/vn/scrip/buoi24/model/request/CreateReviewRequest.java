package vn.scrip.buoi24.model.request;
import lombok.Data;
@Data
public class CreateReviewRequest {
    private Integer movieId;
    private String content;

}

