package com.example.expenseviewerapp.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateParser {

    private static final SimpleDateFormat ISO_8601_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);

    private DateParser() {
    }

    public static synchronized Date parseIso8601(String dateString) throws ParseException {
        return ISO_8601_FORMAT.parse(dateString);
    }
}
