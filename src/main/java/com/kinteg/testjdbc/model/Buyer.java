package com.kinteg.testjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class Buyer {

    private Long id;

    private String name;
    private String country;

    private Integer token;

}
