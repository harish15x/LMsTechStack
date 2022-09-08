package com.bridgelabz.lmstechstack.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClass {

    private String message;
    private int errorCode;
    private Object object;
}
