package com.example.spring_homework28.Model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Integer productId;

    @NotNull(message = "quantity cannot be null")
    private Integer quantity;

}
