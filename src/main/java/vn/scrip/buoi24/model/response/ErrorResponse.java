package vn.scrip.buoi24.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private Object message;
}
