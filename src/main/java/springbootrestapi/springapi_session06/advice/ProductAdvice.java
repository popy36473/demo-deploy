package springbootrestapi.springapi_session06.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import springbootrestapi.springapi_session06.exception.ProductNotFound;
import springbootrestapi.springapi_session06.model.dto.response.ApiDataResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleMethodArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Lỗi thêm mới sản phẩm",
                null,
                errors,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiDataResponse<String>> handleHttpMessage(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Sai kiểu định dạng ngày tháng",
                null,
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ApiDataResponse<String>> handleProductNotFound(ProductNotFound ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Lỗi tìm kiếm sản phẩm",
                null,
                ex.getLocalizedMessage(),
                HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiDataResponse<String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Sai kiểu dữ liệu tham số",
                null,
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
}
