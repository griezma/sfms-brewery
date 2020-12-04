package griezma.springf.msscbrewery.data.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateTimeMapper {
    public OffsetDateTime toDateTime(Timestamp ts) {
        if (ts == null) {
            return  null;
        }
        return OffsetDateTime.ofInstant(ts.toInstant(), ZoneOffset.UTC);
    }

    public Timestamp asTimestamp(OffsetDateTime dt) {
        if (dt == null) {
            return null;
        }
        return Timestamp.valueOf(dt.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }
}