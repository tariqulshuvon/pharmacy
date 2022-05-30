package com.shop.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column (name = "expiry_date")
    @DateTimeFormat(pattern = "mm/dd/yyyy")
    private LocalDate expiryDate;
}
