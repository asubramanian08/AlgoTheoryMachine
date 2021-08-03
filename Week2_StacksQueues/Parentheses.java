package Week2_StacksQueues;

import java.util.Scanner;
import java.util.Stack;

//check is parentheses, brackets, and braces are balanced
public class Parentheses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        in.close();

        Stack<Character> s = new Stack<Character>();
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{')
                s.add(str.charAt(i));
            else if ((s.peek() == '(' && str.charAt(i) == ')') || (s.peek() == '[' && str.charAt(i) == ']')
                    || (s.peek() == '{' && str.charAt(i) == '}'))
                s.pop();
            else
                break;

        if (s.isEmpty())
            System.out.println("True");
        else
            System.out.println("False");
    }
}