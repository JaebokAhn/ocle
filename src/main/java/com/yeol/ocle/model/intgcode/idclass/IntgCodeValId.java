package com.yeol.ocle.model.intgcode.idclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class IntgCodeValId implements Serializable {
    private String intgCodeId;
    private String intgCodeVal;
}
