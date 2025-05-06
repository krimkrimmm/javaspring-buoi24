package vn.scrip.buoi24.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private String content;
    @Min(value = 1, message = "Rating phải lớn hơn hoặc bằng 1")
    @Max(value = 10, message = "Rating phải nhỏ hơn hoặc bằng 10")
    private Integer rating;

    private Integer movieId;
}
