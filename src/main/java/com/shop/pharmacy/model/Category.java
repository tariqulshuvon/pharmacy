package com.shop.pharmacy.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private  String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",cascade = CascadeType.ALL)
    private List<Medicine> medicines;
}
