package com.example.spring_homework28.Model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "quantity cannot be null")
    private Integer quantity;

    private Integer productId;

}
