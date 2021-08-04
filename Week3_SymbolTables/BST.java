package Week3_SymbolTables;

import java.util.Vector;
import java.util.Stack;

//My BST implementation (without remove)
public class BST<Key extends Comparable<Key>, Value> {
    protected class Node {
        Key key;
        Value value;
        Node left, right;

        void set(Key key) {
            this.key = key;
            left = new Node();
            right = new Node();
        }
    }

    protected Node root = new Node();

    public boolean isEmpty() {
        return root.key == null;
    }

    public void put(Key key, Value value) {
        Node nd = find(key);
        if (nd.key == null)
            nd.set(key);
        nd.value = value;
    }

    public Value get(Key key) {
        return find(key).value;
    }

    public boolean contains(Key key) {
        return find(key).key != null;
    }

    public Iterable<Key> keys() {
        Vector<Key> orderedKeys = new Vector<Key>();
        Stack<Node> toAdd = new Stack<Node>();
        toAdd.add(root);
        boolean justPushed = true;
        while (!toAdd.isEmpty())
            if (toAdd.peek().key == null) {
                toAdd.pop();
                justPushed = false;
            } else if (justPushed) {
                toAdd.add(toAdd.peek().left);
                justPushed = true;
            } else {
                orderedKeys.add(toAdd.peek().key);
                toAdd.add(toAdd.pop().right);
                justPushed = true;
            }
        return orderedKeys;
    }

    private Node find(Key key) {
        Node curr = root;
        while (curr.key != null && curr.key != key)
            if (key.compareTo(curr.key) < 0)
                curr = curr.left;
            else
                curr = curr.right;
        return curr;
    }

    public static void main(String[] args) {
        System.out.println("Making a BST with key=String and Value=Integer");
        BST<String, Integer> test = new BST<String, Integer>();
        System.out.println("Get the value of \"hi\": " + test.get("hi"));
        System.out.println("isEmpty returns: " + test.isEmpty());
        System.out.println("Putting (\"hi\",10)");
        test.put("hi", 10);
        System.out.println("Contains \"bob\" is: " + test.contains("bob"));
        System.out.print("The list of all keys is:");
        for (String key : test.keys())
            System.out.print(' ' + key);
        System.out.println();
        System.out.println("Get the value of \"hi\": " + test.get("hi"));
        System.out.println("Get the value of \"bob\": " + test.get("bob"));
        System.out.println("Putting (\"my\",51), (\"name\",-3), (\"is\",2), (\"bob\",0)");
        test.put("my", 51);
        test.put("name", -3);
        test.put("is", 2);
        test.put("bob", 0);
        System.out.println("Get the value of \"bob\": " + test.get("bob"));
        System.out.println("Contains \"bob\" is: " + test.contains("bob"));
        System.out.println("Get the value of \"is\": " + test.get("is"));
        System.out.println("Changing \"is\" to 3");
        test.put("is", 3);
        System.out.println("Get the value of \"is\": " + test.get("is"));
        System.out.print("The list of all keys is:");
        for (String key : test.keys())
            System.out.print(' ' + key);
        System.out.println();
        System.out.println("isEmpty returns: " + test.isEmpty());
    }
}