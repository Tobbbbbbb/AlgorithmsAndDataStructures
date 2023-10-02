import java.io.*;
import java.util.*;

class RopeProblem {
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

	class Vertex {
        char key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        int size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(char key, int size, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.size = size;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.size = 1;
        if (v.left != null) {
            v.left.parent = v;
            v.size+=v.left.size;
        }
        if (v.right != null) {
            v.right.parent = v;
            v.size+=v.right.size;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;
        VertexPair() {
        }
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    /*VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }*/

    //Searches for the key-th index in the tree with the given root.
    Vertex find(Vertex root, int key){
    	Vertex v = root;
    	Vertex last = root;
    	Vertex next = null;
    	int total = key+1;
    	int size;
    	//System.out.println(total);
    	while(v != null) {
    		if(v.left==null){
    			size = 1;
    		} else {
    			size = v.left.size + 1;
    		}
    		//System.out.println(v.key + " " + size);
    		//System.out.println(size + " " + total);
    		last = v;
    		if(size>total){
    			v = v.left;
    		} else if (size<total){
    			total = total-size;
    			v = v.right;
    		} else if (size==total){
    			//System.out.println("e");
    			break;
    		}
    	
    	}
    	next = splay(last);
    	//System.out.println("found " + root.key + root.size);
    	return next;
    }
 
    //splits along the key-th index
    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        root = find(root, key);
        if(root!=null){
	        if(key!=root.size){
		        result.right = root;
		        if(root.left!=null){
		        	result.left = root.left;
		        	result.left.parent = null;
		        	root.left = null;
		        } else {
		        	result.left = null;
		        }
		        update(result.left);
		        if(result.left!=null)
		        //System.out.println("split left " + result.left.key + result.left.size);
		        update(result.right);
		        //System.out.println("split right " + result.right.key + result.right.size);
		        return result;
		    }
		    result.left=root;
		    result.right=null;
		}
    	return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right.left = left;
        update(right);
        //System.out.println("merge " + right.key + right.size);
        return right;
    }

	class Rope {
		Vertex root;

		void process( int i, int j, int k ) {
            VertexPair b = split(root, j+1);
            //System.out.println("b");
            //System.out.println(b.left.key);
            VertexPair a = split(b.left, i);
            //System.out.println("a");
            root = merge(a.left, b.right);
            VertexPair c = split(root, k);
            //System.out.println("c");
            root = merge(merge(c.left, a.right), c.right);
		}

		String result() {
			ArrayList<Character> order = new ArrayList<Character>();
			Stack<Vertex> stack = new Stack<Vertex>();
			Vertex current = root;
			stack.push(current);
			current = current.left;
			Vertex pop;
			while(!stack.empty()){
				while(current!=null){
					stack.push(current);
					current = current.left;
				}
				if(current==null && !stack.empty()){
					current = stack.pop();
					order.add(current.key);
					current = current.right;
					if(current!=null){
						stack.push(current);
						current = current.left;
					}
				}
			}
			StringBuilder toReturn = new StringBuilder();
			for(char ch : order){
				toReturn.append(ch);
			}
			return toReturn.toString();
		}

		Rope( String s ) {
			root = new Vertex(s.charAt(s.length()-1), s.length(), null, null, null);
			Vertex cur = root;
			Vertex temp;
			for(int i=s.length()-2; i>=0; i--){
				temp = new Vertex(s.charAt(i), i+1, null, null, cur);
				//System.out.println(temp.size);
				cur.left = temp;
				//System.out.println(cur.key);
				//System.out.println(cur.left.key);
				cur=temp;
			}
			/*root = new Vertex(s.charAt(0), 1, null, null, null);
			Vertex last = root;
			for(int i=1; i<s.length(); i++){
				root = new Vertex(s.charAt(i),i+1, last, null, null);
				last.parent = root;
				last = root;
			}*/
		}
	}

	public static void main( String[] args ) throws IOException {
		new RopeProblem().run();
	}
	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		Rope rope = new Rope(in.next());
		for (int q = in.nextInt(); q > 0; q--) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			rope.process(i, j, k);
		}
		out.println(rope.result());
		out.close();
	}
}
