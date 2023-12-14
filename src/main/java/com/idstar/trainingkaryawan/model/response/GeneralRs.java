package com.idstar.trainingkaryawan.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralRs<T> {
    private int code;
    private T data;
    private String status;
}
