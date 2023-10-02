import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        ArrayList<Integer> order;
        ArrayList<Long> depthArr;

        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        /*List<Integer> inOrder() {
            order = new ArrayList<Integer>();
            depthArr = new ArrayList<Long>();
            subInOrder(0, 0);
            return order;
        }

        void subInOrder(int index, long depth){
            if(tree[index].left!=-1){
                subInOrder(tree[index].left, depth+1);
            }
            order.add(tree[index].key);
            depthArr.add(depth);
            if(tree[index].right!=-1){
                subInOrder(tree[index].right, depth+1);
            }
        }*/

        boolean isBinarySearchTree() {
            if(tree.length==0){
                return true;
            }
            /*inOrder();
            for(int i=0; i<order.size()-1; i++){
                if(order.get(i+1)<order.get(i)){
                    return false;
                }
                if((order.get(i+1)==order.get(i)) && (depthArr.get(i+1)<=depthArr.get(i))){
                    return false;
                }
            }
            return true;*/
            if(!inOrder()){
                return false;
            } else {
                for(int i=0; i<order.size()-1; i++){
                    if(order.get(i+1)<order.get(i)){
                        return false;
                    }
                }
                return true;
            }
        }

        boolean inOrder() {
            order = new ArrayList<Integer>();
            return subInOrder(0);
        }

        boolean subInOrder(int index){
            if(tree[index].left!=-1){
                if(tree[tree[index].left].key>=tree[index].key){
                    return false;
                }
                if(!subInOrder(tree[index].left)){
                    return false;
                }
            }
            order.add(tree[index].key);
            if(tree[index].right!=-1){
                if(tree[tree[index].right].key<tree[index].key){
                    return false;
                }
                if(!subInOrder(tree[index].right)){
                    return false;
                }
            }
            return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
