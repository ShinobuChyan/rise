//package github.shinobu.rise.datastructure.tree;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Stack;
//
///**
// * @author Shinobu
// * @since 2019/8/12
// */
//public class BinaryTree<T> {
//
//    public static void main(String[] args) {
//        var t = new BinaryTree(8, 8, 9, 7, 3, 1, 4, 9);
//        t.postorderTraversal();
//    }
//    private Node<T> root;
//
//    class Node<NT> {
//
//        NT val;
//
//        Node<NT> left;
//
//        Node<NT> right;
//
//        public Node(NT val, Node<NT> left, Node<NT> right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    BinaryTree(T... val) {
//        if (val == null || val.length <= 1) {
//            throw new RuntimeException("can't construct a tree");
//        }
//
//        var nodeArr = new Node[val.length];
//        T rootV = null;
//        int delta = 1;
//        for (int i = 0; i < val.length; i++) {
//            if (rootV == null && val[i] != null) {
//                rootV = val[i];
//                nodeArr[i] = root = new Node<>(rootV, null, null);
//                continue;
//            }
//
//            if (val[i] != null) {
//                var n = new Node<>(val[i], null, null);
//                nodeArr[i] = n;
//                if (delta == 1) {
//                    nodeArr[(i - delta++) / 2].left = n;
//                } else {
//                    nodeArr[(i - delta--) / 2].right = n;
//                }
//            }
//        }
//    }
//
//    void inorderTraversal() {
//        var stack = new LinkedList<Node<T>>();
//        var p = root;
//        stack.push(p);
//
//        while (p != null || !stack.isEmpty()) {
//            while (p != null && p.left != null) {
//                stack.push(p = p.left);
//            }
//
//            p = stack.pop();
//            System.out.println(p.val);
//            p = p.right;
//            if (p != null) {
//                stack.push(p);
//            }
//        }
//    }
//
//    void postorderTraversal() {
//        var nStack = new LinkedList<Node<T>>();
//        var rStack = new LinkedList<Node<T>>();
//        var p = root;
//        nStack.push(p);
//
//        boolean left = false;
//        while (p != null || !nStack.isEmpty()) {
//            if (!left && p != null && p.left != null) {
//                rStack.push(p);
//            }
//            if (p != null) {
//                if (left) {
//                    nStack.push(p = p.left);
//                    left = false;
//                    continue;
//                } else {
//                    if (p.right != null) {
//                        nStack.push(p = p.right);
//                    } else {
//                        p = rStack.isEmpty() ? null : rStack.pop();
//                        left = true;
//                    }
//                    continue;
//                }
//            }
//
//            System.out.println(nStack.pop().val);
//        }
//    }
//
//}
