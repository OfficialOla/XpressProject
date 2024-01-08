package com.example.ProjectXpress.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    @Data
    public static class ProductResponseData {
        private boolean hasNextRecord;
        private int totalCount;
        private List<ProductDto> productDTOList;
    }
}
