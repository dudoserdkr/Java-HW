package edu.hw8;

public class B08_02 {

    public static boolean isBracketsSequenceCorrect(String input) {
        if (input == null) {
            return true;
        }

        B08_01.RecursiveStack<Character> stack = new B08_01.RecursiveStack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpeningBracket(c)) {
                stack.push(c);
            } else if (isClosingBracket(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.pop();
                if (!bracketsMatch(open, c)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpeningBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosingBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean bracketsMatch(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        System.out.println(isBracketsSequenceCorrect("()[]{}"));
        System.out.println(isBracketsSequenceCorrect("([{}])"));
        System.out.println(isBracketsSequenceCorrect("([)]"));
        System.out.println(isBracketsSequenceCorrect("((("));
        System.out.println(isBracketsSequenceCorrect("({[]})[]{}"));
    }
}
