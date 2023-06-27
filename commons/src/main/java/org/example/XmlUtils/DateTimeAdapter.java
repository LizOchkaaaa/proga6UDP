package org.example.xmlUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {
    final static DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");

    @Override
    public String marshal(ZonedDateTime v) {
        ZonedDateTime zdtInLocalTimeline = v.withZoneSameInstant(ZoneId.systemDefault());
        return zdtInLocalTimeline.toString();
    }

    @Override
    public ZonedDateTime unmarshal(String v) throws ParseException {
        /*преобразования стороки в объект*/
        return ZonedDateTime.parse(v);
    }
}