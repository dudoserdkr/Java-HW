package edu.hw6;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class B6_02 {
    public static void main(String[] args) {
        String s = """
            Call me: +380(67)-123-45-67, +380 44 555 55 55, 0671234567,
            (044)555-55-55, 044-555-55-55, 380671234567, bogus 123-45, +12(34)56
            """;

        // + optional, country code optional, area code with/without (), 3-2-2 blocks with optional - or space
        String regex =
            "(?<!\\d)" +                                         // not glued to other digits on the left
            "(?:\\+?\\d{1,3}[- ]?)?" +                           // optional +CC
            "(?:\\(\\d{2,3}\\)|\\d{2,3})[- ]?" +                 // (XX/XXX) or XX/XXX
            "\\d{3}[- ]?\\d{2}[- ]?\\d{2}" +                     // 3-2-2
            "(?!\\d)";                                           // not glued on the right

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.println("Found: '" + m.group() + "' at [" + m.start() + "," + (m.end() - 1) + "]");
        }
    }
}
