package Week6_Intractability;

import java.util.ArrayList;
import java.util.Scanner;

//iterates over all 2^n subsets
public class ZeroSum {
    private static String iterate(ArrayList<Integer> numbers, int idx, int val) {
        if (idx == numbers.size()) {
            if (val != 0)
                return null;
            ArrayList<Integer> ans = new ArrayList<Integer>();
            for (Integer num : numbers)
                if (num != 0)
                    ans.add(num);
            return ans.size() != 0 ? ans.toString() : "No subset";
        }
        String ans = iterate(numbers, idx + 1, val + numbers.get(idx));
        if (ans != null)
            return ans;
        int temp = numbers.get(idx);
        numbers.set(idx, 0);
        ans = iterate(numbers, idx + 1, val);
        numbers.set(idx, temp);
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
            numbers.add(in.nextInt());
        in.close();
        String subset = iterate(numbers, 0, 0);
        System.out.println(subset);
    }
}