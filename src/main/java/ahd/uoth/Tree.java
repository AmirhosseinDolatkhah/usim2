package ahd.uoth;

import java.util.*;

public class Tree {
    public static class Node {
        char value;
        Node left, right;

        Node(char item) {
            value = item;
            left = right = null;
        }

        @Override
        public String toString() {
            return left == null && right == null ? String.valueOf(value) : "(" + left + value + right + ")";
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '^';
    }

    private final List<String> results = new ArrayList<>();
    private void inorder(Node t) {
        if (t != null) {
            inorder(t.left);
            results.add(t.toString());
            inorder(t.right);
        }
    }

    private Node constructTree(char[] postfix) {
        Stack<Node> st = new Stack<>();
        Node t, t1, t2;

        for (char c : postfix) {
            t = new Node(c);

            if (c == '~') {

            } else if (isOperator(c)) {
                t1 = st.pop();
                t2 = st.pop();
                t.right = t1;
                t.left = t2;
            }

            st.push(t);
        }

        t = st.peek();
        st.pop();

        return t;
    }

    public static void main(String[] args) {
        Tree et = new Tree();
        Scanner postfix = new Scanner (System.in);
        char[] charArray = postfix.nextLine().toCharArray();
        Node root = et.constructTree(charArray);
        et.results.clear();
        et.inorder(root);
        System.out.println(charArray[0] == '~' ? "(~" +
                Collections.max(et.results, Comparator.comparingInt(String::length)) + ")" :
                Collections.max(et.results, Comparator.comparingInt(String::length)));
    }
}