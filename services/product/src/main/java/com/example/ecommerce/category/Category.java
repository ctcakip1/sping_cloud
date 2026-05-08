package com.example.ecommerce.category;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.example.ecommerce.product.dto.Product;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
