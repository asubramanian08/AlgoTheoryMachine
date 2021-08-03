package Week2_StacksQueues;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class InfixToPostfix {
    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
            case "sqrt":
                return 3;
            default:
                return 0;
        }
    }

    private static boolean isAlpha(String val) {
        if (val.length() != 1)
            return false;
        char ch = val.charAt(0);
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isNum(String val) {
        try {
            Double.parseDouble(val);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String val;
        ArrayList<String> postfix = new ArrayList<String>();
        Stack<String> oper = new Stack<String>();
        oper.add("");
        while (in.hasNext()) {
            val = in.next();
            if (isAlpha(val) || isNum(val))
                postfix.add(val);
            else if (val.equals(")")) {
                while (oper.peek() != "(")
                    postfix.add(oper.pop());
                oper.pop();
            } else {
                while (precedence(val) <= precedence(oper.peek()))
                    postfix.add(oper.pop());
                oper.push(val);
            }
        }
        in.close();
        while (oper.peek() != "")
            postfix.add(oper.pop());
        for (String str : postfix)
            System.out.print(str + ' ');
    }
}