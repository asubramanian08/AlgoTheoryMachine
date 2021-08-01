package Week1_SearchSort;

// A non-recursive binary search
public class Binary {
    public static int search(String key, String[] a) {
        int lo = 0, hi = a.length, mid, cmp;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            cmp = a[mid].compareTo(key);
            if (cmp > 0)
                hi = mid;
            else if (cmp < 0)
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] a = { "abc", "de", "f", "ge" };
        System.out.print("The strings are:");
        for (String str : a)
            System.out.print(' ' + str);
        System.out.println();
        System.out.println("\"abc\" is in position " + search("abc", a));
        System.out.println("\"f\" is in position " + search("f", a));
        System.out.println("\"fg\" is in position " + search("fg", a));
    }
}