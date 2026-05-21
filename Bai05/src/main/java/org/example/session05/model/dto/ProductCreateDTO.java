package org.example.session05.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateDTO {
    @NotBlank(message = "Product name can not blank")
    private String productName;
    @NotBlank(message = "Description can not blank")
    private String description;
    @NotNull(message = "Price can not null")
    @Min(value = 0,message = "Price min = 0")
    private Double price;
    @NotNull(message = "Stock can not null")
    @Min(value = 1,message = "Stock min = 1")
    private Integer stock ;
}
