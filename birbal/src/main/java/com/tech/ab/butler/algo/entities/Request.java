package com.tech.ab.butler.algo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Created by shreenath on 12/1/17.
 */
@AllArgsConstructor
@Getter
@Data
public class Request {
    private Timestamp requestTime;
    private String place;
}
