package Week3_SymbolTables;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

//generates strings OR takes all the strings from input, the values are a random number
//gets 50% of the original strings and 50% random strings, the output is discarded
public class TestBST {
    public static final int defaultStrLen = 15;

    public static String[] RandomTest(String[] args) {
        int test_sz = Integer.parseInt(args[0]);
        int strLen = defaultStrLen;
        if (args.length == 2)
            strLen = Integer.parseInt(args[1]);
        Random rand = new Random();
        String[] strings = new String[test_sz];
        byte[] gen = new byte[strLen];
        for (int i = 0; i < test_sz; i++) {
            rand.nextBytes(gen);
            strings[i] = new String(gen);
        }
        return strings;
    }

    public static String[] DatasetTest() {
        ArrayList<String> strings = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
            strings.add(in.next());
        in.close();
        return strings.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // prep - strings, values, BST init
        BST<String, Integer> test = new BST<String, Integer>();
        String[] strings = args.length > 0 ? RandomTest(args) : DatasetTest();
        Random rand = new Random();
        int[] values = new int[strings.length];
        for (int i = 0; i < values.length; i++)
            values[i] = rand.nextInt();

        // test put
        long start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; i++)
            test.put(strings[i], values[i]);
        long end = System.currentTimeMillis();
        System.out.println("Putting all the values: " + (end - start) + " milliseconds");

        // generate random strings for 50%
        byte[] gen = new byte[defaultStrLen];
        for (int i = strings.length / 2; i < strings.length; i++) {
            rand.nextBytes(gen);
            strings[i] = new String(gen);
        }

        // test get
        start = System.currentTimeMillis();
        for (int i = 0; i < strings.length; i++)
            test.get(strings[i]);
        end = System.currentTimeMillis();
        System.out.println("Getting 50% original and 50% random: " + (end - start) + " milliseconds");
    }
}