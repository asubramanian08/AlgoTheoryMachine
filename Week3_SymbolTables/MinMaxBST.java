package Week3_SymbolTables;

//Adding min and max functions to BST
public class MinMaxBST<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    private Key pick(Node nd, boolean getMin) {
        if (nd.key == null)
            return null;
        Key ans;
        if (getMin)
            ans = pick(nd.left, getMin);
        else
            ans = pick(nd.right, getMin);
        if (ans == null)
            ans = nd.key;
        return ans;
    }

    public Key min() {
        return pick(root, true);
    }

    public Key max() {
        return pick(root, false);
    }
}