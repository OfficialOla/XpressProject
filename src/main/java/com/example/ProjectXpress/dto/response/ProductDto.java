package com.example.ProjectXpress.dto.response;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ProductDto {
    private String name;
    private String uniqueCode;
    private boolean lookUp;
    private boolean fixedAmount;
    private int amount;
    private int minimumAmount;
    private int maximumAmount;
    private String imageUrl;
    private String billerName;
    private CategoryDto categoryDTO;
}
