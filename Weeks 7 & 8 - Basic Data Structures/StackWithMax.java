import java.util.*;
import java.io.*;

public class StackWithMax {

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

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> max = new Stack<Integer>();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
                if(max.empty() || max.peek()<=value){
                    max.push(value);
                }
            } else if ("pop".equals(operation)) {
                if(max.peek().equals(stack.peek())){
                    max.pop();
                }
                stack.pop();
            } else if ("max".equals(operation)) {
                System.out.println(max.peek());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
