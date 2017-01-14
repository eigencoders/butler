package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Created by shreenath on 12/1/17.
 */
@AllArgsConstructor
@Getter
public class Request {
    private Timestamp requestTime;
    private String place;
}
