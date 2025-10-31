package edu.hw6;

import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class B6_01 {
    public static void main(String[] args) {
        String regex = "(?:(?<day>0[1-9]|[12][0-9]|3[01])\\.(?<month>0[1-9]|1[0-2])\\.(?<year>\\d{4})|__\\.__\\.____)";
        String s = "17.01.2006abcdefgh.__.__.____";

        String ddMMyyyy = LocalDate.now(ZoneId.of("Europe/Kyiv"))
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        String out = m.replaceAll(Matcher.quoteReplacement(ddMMyyyy));
        System.out.println(out);
    }
}
