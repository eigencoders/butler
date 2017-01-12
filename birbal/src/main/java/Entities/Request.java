package Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by shreenath on 12/1/17.
 */
@AllArgsConstructor
@Getter
public class Request {
    private long currentTimestamp;
    private String place;
}
