/* Description: Uses a stack to check whether (), [] and {} in a user-entered
 * sequence are properly matched and nested.
 * Programmed by: Duezel Kurt N. Dioso BSIT 48079 Data Structures
 * Last Modified: October 23, 2026
 * Version: 1.0
 * [Acknowledgements: ChatGPT, Claude, Wikipedia.] */

import java.util.Scanner;
import java.util.Stack;

public class ModifiedStack_DelimiterCheckerTrace {

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
            
            System.out.print("Enter delimiter sequence: ");
            String input = sc.nextLine();
            
            System.out.println("\nInput: " + input);
            
            for (int i = 0; i < input.length(); i++) {
                
                char current = input.charAt(i);
                
                if (isOpening(current)) {
                    
                    stack.push(current);
                    
                    System.out.println("\nCharacter: " + current);
                    System.out.println("Operation: PUSH");
                    System.out.println("Stack: " + displayStack(stack));
                    
                } else if (isClosing(current)) {
                    
                    System.out.println("\nCharacter: " + current);
                    
                    if (stack.isEmpty()) {
                        System.out.println("Operation: INVALID");
                        System.out.println(
                                "Reason: No matching opening delimiter."
                        );
                        System.out.println("Result: Invalid");
                        sc.close();
                        return;
                    }
                    
                    char top = stack.peek();
                    
                    System.out.println("Operation: PEEK");
                    System.out.println("Top element: " + top);
                    
                    if (matches(top, current)) {
                        
                        stack.pop();
                        
                        System.out.println("Operation: POP");
                        System.out.println(
                                "Removed delimiter: " + top
                        );
                        System.out.println(
                                "Stack: " + displayStack(stack)
                        );
                        
                    } else {
                        
                        System.out.println("Operation: INVALID");
                        System.out.println(
                                "Reason: Mismatched delimiters."
                        );
                        System.out.println(
                                current + " does not match " + top
                        );
                        System.out.println(
                                "Stack: " + displayStack(stack)
                        );
                        System.out.println("Result: Invalid");
                        
                        sc.close();
                        return;
                    }
                }
            }
            
            System.out.println();
            
            if (stack.isEmpty()) {
                System.out.println("Result: Valid");
                System.out.println("All delimiters are matched.");
            } else {
                System.out.println("Result: Invalid");
                System.out.println(
                        "Unmatched opening delimiters remain: "
                                + displayStack(stack)
                );
            }
        }
    }
}

