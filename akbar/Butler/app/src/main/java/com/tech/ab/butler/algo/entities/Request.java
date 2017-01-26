package com.tech.ab.butler.algo.entities;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by shreenath on 12/1/17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
public class Request {
    private Timestamp requestTime;
    private String place;
}
