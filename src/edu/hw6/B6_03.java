package edu.hw6;

import java.util.regex.Pattern;

public class B6_03 {
    public static void main(String[] args) {
        String[] tests = {
            "+2 - 57*33 + 25/ - 4",
            "  -10   +   3*5 -   +7   /2   ",
            "5++6",           // error
            "7/   ",          // error
            "  42",           // ок
            " +  25/ - 4",    // ок
            "2 - - - 3",      // error
            "2--3",           // error
            "2 - -3 + +4* -5" // ок
        };

        String dashRegex = "[\\u2012\\u2013\\u2014\\u2015]";

        String exprRegex = "\\s*[+-]?\\d+\\s*(?:[+\\-*/]\\s*[+-]?\\d+\\s*)*";

        Pattern expr = Pattern.compile(exprRegex);

        for (String raw : tests) {
            String s = raw.replaceAll(dashRegex, "-");
            boolean ok = expr.matcher(s).matches();
            System.out.println("'" + raw + "' -> " + ok);
        }
    }
}
