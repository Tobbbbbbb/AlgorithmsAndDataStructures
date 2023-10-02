import java.util.*;
import java.io.*;

public class substring_equality {

	private static long multiplier = 314;
	private static long hashes[][];
	static long m1 = 1000000007;
	static long m2 = 1000000009;

	public class Solver {
		private String s;
		public Solver(String s) {
			this.s = s;
		}
		public boolean ask(int a, int b, int l) {
			return (hashFunc(a, l, 0)%m1 == hashFunc(b, l, 0)%m1)
			&& (hashFunc(a, l, 1)%m2 == hashFunc(b, l, 1)%m2);
		}
	}


	private static void fillHashTable(String s){
		hashes = new long[s.length()+1][4];
		hashes[0][0] = 0;
		hashes[0][1] = 0;
		hashes[0][2] = 1;
		hashes[0][3] = 1;
        for (int i = 0; i < s.length(); i++) {
            hashes[i+1][0] = ((hashes[i][0] * multiplier) + s.charAt(i)-'a') % m1;
            hashes[i+1][1] = ((hashes[i][1] * multiplier) + s.charAt(i)-'a') % m2;
        	hashes[i+1][2] = (hashes[i][2] * multiplier) % m1;
        	hashes[i+1][3] = (hashes[i][3] * multiplier) % m2;
        }
	}

	private long hashFunc(int c, int l, int m){
	    if(m==0){
		    return ((hashes[c+l][m] - hashes[l][m+2]*hashes[c][m])%m1+m1)%m1;
	    } else {
	        return ((hashes[c+l][m] - hashes[l][m+2]*hashes[c][m])%m2+m2)%m2;
	    }
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		fillHashTable(s);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();
			out.println(solver.ask(a, b, l) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}

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
}
