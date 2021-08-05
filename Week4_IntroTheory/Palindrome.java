package Week4_IntroTheory;

import java.util.Scanner;

//takes strings in input and prints whether it is a palindrome
public class Palindrome {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            int lo = 0, hi = str.length() - 1;
            while (lo < hi)
                if (Character.isWhitespace(str.charAt(lo)))
                    lo++;
                else if (Character.isWhitespace(str.charAt(hi)))
                    hi--;
                else if (str.charAt(lo) == str.charAt(hi)) {
                    lo++;
                    hi--;
                } else
                    break;
            System.out.println("\"" + str + "\" is" + (lo < hi ? " not" : "") + " a palindrome");
        }
        in.close();
    }
}