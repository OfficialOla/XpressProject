package com.example.ProjectXpress.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeDetails {
    private String phoneNumber;
    private int amount;
}
