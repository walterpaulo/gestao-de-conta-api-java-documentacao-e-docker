package com.example.demo.models.model_views;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String erros;

    public void setData(T data) {
        this.data = data;
    }
}
