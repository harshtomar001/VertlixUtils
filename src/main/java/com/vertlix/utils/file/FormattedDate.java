package com.vertlix.utils.file;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class FormattedDate {
        public static String date() {
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.ENGLISH);

            String formattedDate = now.format(formatter).toUpperCase();

            return  formattedDate;
        }


}
