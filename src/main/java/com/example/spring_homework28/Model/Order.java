package com.example.spring_homework28.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "myOrder")
public class Order {
    //id - quantity - totalPrice - dateReceived - status(new-inProgress-completed)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "quantity cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @NotNull(message = "totalPrice cannot be null")
    @Column(columnDefinition = "decimal not null")
    private Double totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date dateReceived;

    @NotEmpty(message = "status cannot be empty")
    @Column(columnDefinition = "varchar(25) not null check (status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;




}
