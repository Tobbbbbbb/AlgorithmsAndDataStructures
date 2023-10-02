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
		ArrayList<Integer> in;
		ArrayList<Integer> pre;
		ArrayList<Integer> post;
		
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
			in = new ArrayList<Integer>();
			subInOrder(0);            
			return in;
		}

		void subInOrder(int index){
			if(left[index]!=-1){
				subInOrder(left[index]);
			}
			in.add(key[index]);
			if(right[index]!=-1){
				subInOrder(right[index]);
			}
		}

		List<Integer> preOrder() {
			pre = new ArrayList<Integer>();
            subPreOrder(0);
			return pre;
		}

		void subPreOrder(int index){
			pre.add(key[index]);
			if(left[index]!=-1){
				subPreOrder(left[index]);
			}
			if(right[index]!=-1){
				subPreOrder(right[index]);
			}
		}

		List<Integer> postOrder() {
			post = new ArrayList<Integer>();
            subPostOrder(0);
			return post;
		}

		void subPostOrder(int index){
			if(left[index]!=-1){
				subPostOrder(left[index]);
			}
			if(right[index]!=-1){
				subPostOrder(right[index]);
			}
			post.add(key[index]);
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
