package com.example.exception_handling_bootcamp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<T> {
    private Integer status;
    private String errorMessage;
    private Date createDate;
}
