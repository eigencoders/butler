package constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by shreenath on 13/1/17.
 */
public class ComputeConstants {
    public static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static final String PKEY = "place";
    public static final String TKEY = "time";
    public static final String UKEY = "urgency";
    public static final String IKEY = "inherent";
    public static final String DKEY = "dependency";

    public static final long ONE_HOUR_MS = 3600000;
    public static final long TWO_HOUR_MS = 2*ONE_HOUR_MS;
    public static final long FOUR_HOUR_MS = 4*ONE_HOUR_MS;
    public static final long SIX_HOUR_MS = 6*ONE_HOUR_MS;
    public static final long MINS_2_MS = 60000;



}
