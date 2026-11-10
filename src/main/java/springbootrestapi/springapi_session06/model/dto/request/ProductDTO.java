package springbootrestapi.springapi_session06.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {
    @NotBlank(message = "Không được để trống tên sản phẩm")
    private String proName;
    @NotBlank(message = "Không được để trống nhà sản xuất")
    private String producer;
    @NotNull(message = "Không được để trống năm sản xuất")
    private Integer yearMaking;
    @NotNull(message = "Không được để trống ngày hết hạn")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;
    @NotNull(message = "Không được để trống giá sản phẩm")
    @Min(value = 0, message = "Giá sản phẩm không được âm")
    private Double price;
}
