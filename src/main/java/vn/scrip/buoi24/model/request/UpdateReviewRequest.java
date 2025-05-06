package vn.scrip.buoi24.model.request;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReviewRequest {
    private String content;
    @Min(value = 1, message = "Rating phải lớn hơn hoặc bằng 1")

    @Max(value = 10, message = "Rating phải nhỏ hơn hoặc bằng 10")
    private Integer rating;
}