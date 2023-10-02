import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		//int child[][];
		int height[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		/*int computeHeight() {
			child = new int[parent.length][parent.length];
			height = new int[parent.length];
			int root = -1;
			int j;
			for(int i=0; i<parent.length; i++){
				if(parent[i]==-1){
					root = i;
				} else {
					j=0;
					while(child[parent[i]][j]>0){
						j++;
					}
						child[parent[i]][j] = i;
				}
			}

			PriorityQueue<Integer> q = new PriorityQueue<Integer>();
			q.add(root);
			int node;
			int maxHeight = 0;
			String[] split;
			while(!q.isEmpty()){
				node = q.remove();
				for(int i=0; child[node].length>0; i++){
					q.add(child[node][i]);
					height[child[node][i]] = height[node] + 1;
				}
				maxHeight = Math.max(height[node]+1, maxHeight);
			}
			
			return maxHeight;
		}*/

		int computeHeight() {
			height = new int[parent.length];
			int max = 0;
			for(int i=0; i<parent.length; i++){
				if(height[i]==0){
					max = Math.max(max, subHeight(i));
				}
			}

			return max;
		}

		int subHeight(int index){
			if(parent[index]==-1){
				height[index]=1;
				return 1;
			} else if(height[index]!=0){
				return height[index];
			}
			height[index] = subHeight(parent[index]) + 1;
			return height[index];
		}

		/*int computeHeight() {
			child = new String[parent.length];
			for(int i=0; i<parent.length; i++){
			    child[i] = "";
			}
			int neg = -1;
			for(int i=0; i<parent.length; i++){
				if(parent[i]==-1){
					neg = i;
				} else {
					child[parent[i]] = child[parent[i]] + "," + i;
				}
			}
			return subHeight(neg)+1;
		}

		int subHeight(int n) {
			int max = 0;
			if(child[n].length()==0){
				return 0;
			}
			String[] temp = child[n].split(",");
			for(int i=1; i<temp.length; i++){
				max = Math.max(max, subHeight(Integer.parseInt(temp[i])));
			}
			return max+1;
		}*/
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
