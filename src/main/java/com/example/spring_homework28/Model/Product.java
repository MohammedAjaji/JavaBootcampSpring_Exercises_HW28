package com.example.spring_homework28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
//id - name - price (Add All Required Validation)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty ")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotNull(message = "price cannot be null")
    @Column(columnDefinition = "decimal not null")
    private Double price;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "product")
    private Set<Order> orderSet;

}
