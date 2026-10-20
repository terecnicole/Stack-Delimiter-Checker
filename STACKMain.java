
/*
Description: This program uses a stack to check if delimiters are properly matched.
Programmed by: Nicole S. Terec 
Course: BSIT 
CN:48160 
Subject:DATA STRUCTURES AND ALGORITHMS 
Last Modified: October 20, 2026
Version: 1.0
Acknowledgements: OpenAI ChatGPT, used for code drafting and explanation.
Prompt: "Create a Java stack-based delimiter checker that
checks matching delimiters and displays stack operations."

*/

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static boolean isOpening(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    public static boolean isClosing(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    public static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();

        System.out.print("Enter a sequence: ");
        String input = sc.nextLine();

        boolean valid = true;
        String reason = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Ignore characters that are not delimiters
            if (!isOpening(ch) && !isClosing(ch)) {
                continue;
            }

            System.out.println("\nRead: " + ch);

            if (isOpening(ch)) {
                stack.push(ch);
                System.out.println("Operation: PUSH " + ch);
                System.out.println("Stack: " + stack);
            } else {
                if (stack.isEmpty()) {
                    System.out.println("Operation: INVALID");
                    reason = "Closing delimiter has no matching opening delimiter.";
                    valid = false;
                    System.out.println("Reason: " + reason);
                    break;
                }

                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                if (isMatching(top, ch)) {
                    stack.pop();
                    System.out.println("Operation: POP " + top);
                    System.out.println("Stack: " + stack);
                } else {
                    System.out.println("Operation: INVALID");
                    reason = "Mismatched delimiters: " + top
                           + " does not match " + ch + ".";
                    valid = false;
                    System.out.println("Reason: " + reason);
                    System.out.println("Stack: " + stack);
                    break;
                }
            }
        }

        if (valid && !stack.isEmpty()) {
            valid = false;
            reason = "Unmatched opening delimiter remains: " + stack;
            System.out.println("\nReason: " + reason);
        }

        System.out.println("\nFinal Result: "
                + (valid ? "Valid" : "Invalid"));

        sc.close();
    }
}
