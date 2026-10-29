/* Description: Uses a stack to check whether (), [] and {} in a user-entered
 * sequence are properly matched and nested.
 * Programmed by: Duezel Kurt N. Dioso BSIT 48079 Data Structures
 * Last Modified: October 23, 2026
 * Version: 1.0
 * [Acknowledgements: ChatGPT, Claude, Wikipedia.] */

import java.util.Scanner;
import java.util.Stack;

public class DelimiterCheckerTrace {
    static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }
    static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    static boolean matches(char open, char close) {
        if (open == '(' && close == ')') return true;
        if (open == '[' && close == ']') return true;
        return open == '{' && close == '}';
    }
    static String displayStack(Stack<Character> stack) {
        if (stack.isEmpty()) {
            return "Empty";
        }
        return stack.toString();
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Stack<Character> stack = new Stack<>();
            System.out.print("Enter a sequence: ");
            String input = sc.nextLine();
            for (int i = 0; i < input.length(); i++) {
                char current = input.charAt(i);
                if (isOpening(current)) {
                    stack.push(current);
                } else if (isClosing(current)) {
                    if (stack.isEmpty()) {
                        System.out.println(
                            "Invalid: closing delimiter '" + current
                            + "' has no matching opening delimiter."
                        );
                        return;
                    }
                    char top = stack.peek();
                    if (matches(top, current)) {
                        stack.pop();
                    } else {
                        System.out.println(
                            "Invalid: mismatched delimiters, '"
                            + current + "' does not match '"
                            + top + "' at the top of the stack."
                        );
                        return;
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println("Valid: all delimiters are matched.");
            } else {
                System.out.println(
                    "Invalid: unmatched opening delimiters remain "
                    + displayStack(stack) + "."
                );
            }
        }
    }
}
