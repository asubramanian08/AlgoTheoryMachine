package Week1_SearchSort;

import java.util.Scanner;

//print sorted input with no duplicated
public class SingleFilter {
    private static String[] aux;

    private static void Merge(String[] arr, int lo, int hi) {
        int len = hi - lo, mid = lo + len / 2;
        if (len == 1)
            return;
        Merge(arr, lo, mid);
        Merge(arr, mid, hi);
        int j = lo, k = mid;
        for (int i = 0; i < len; i++)
            if (j == mid)
                aux[i] = arr[k++];
            else if (k == hi)
                aux[i] = arr[j++];
            else if (arr[j].compareTo(arr[k]) > 0)
                aux[i] = arr[k++];
            else
                aux[i] = arr[j++];
        for (int i = 0; i < len; i++)
            arr[lo + i] = aux[i];
    }

    public static void main(String[] args) {
        int len = Integer.parseInt(args[0]);
        Scanner in = new Scanner(System.in);
        aux = new String[len];
        String[] arr = new String[len];
        for (int i = 0; i < len; i++)
            arr[i] = in.next();
        in.close();
        Merge(arr, 0, arr.length);
        for (int i = 0; i < arr.length; i++)
            if (i == 0 || arr[i].compareTo(arr[i - 1]) != 0)
                System.out.println(arr[i]);
    }
}