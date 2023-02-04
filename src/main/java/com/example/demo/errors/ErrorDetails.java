package com.example.demo.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetails {
    private String message;
}