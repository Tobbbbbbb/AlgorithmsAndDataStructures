import java.util.*;
import java.io.*;

public class max_sliding_window {

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

    public void solve() throws IOException{
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
        for(int i=0; i<n; i++){
            while(deque.size()>0 && deque.getLast()<arr[i]){
                deque.removeLast();
            }
            deque.add(arr[i]);
            if(i>=m){
                if(deque.peekFirst()==arr[i-m]){
                    deque.removeFirst();
                }
            }
            if(i>=m-1){
                System.out.println(deque.peekFirst());
            }
        }
    }

    static public void main(String[] args) throws IOException {
        new max_sliding_window().solve();
    }
}
