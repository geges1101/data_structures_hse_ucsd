//package com.company;

import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            inOrderTraversal(0, result);
            return result;
        }

        void inOrderTraversal(int idx, ArrayList<Integer> res){
            if(left[idx] != -1) inOrderTraversal(left[idx], res);
            res.add(key[idx]);
            if(right[idx] != -1) inOrderTraversal(right[idx],res);
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            preOrderTraversal(0, result);
            return result;
        }

        void preOrderTraversal(int idx, ArrayList<Integer> res){
            res.add(key[idx]);
            if(left[idx] != -1) preOrderTraversal(left[idx], res);
            if(right[idx] != -1) preOrderTraversal(right[idx],res);
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            postOrderTraversal(0, result);
            return result;
        }

        void postOrderTraversal(int idx, ArrayList<Integer> res){
            if(left[idx] != -1) postOrderTraversal(left[idx], res);
            if(right[idx] != -1) postOrderTraversal(right[idx],res);
            res.add(key[idx]);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
