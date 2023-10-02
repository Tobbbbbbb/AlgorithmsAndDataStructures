import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    private static long prime = 1000000007;
    private static long multiplier = 1;

    private static long[] hashes;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash;
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void precomputeHashes(String s, String t){
        hashes = new long[t.length()-s.length()+1];
        String str = t.substring(t.length()-s.length());
        hashes[t.length()-s.length()] = hashFunc(str);
        long y=1;
        for(int i=0; i<s.length(); i++){
            y = ((y*multiplier)%prime);
        }
        for(int i=t.length()-s.length()-1; i>=0; i--){
            hashes[i] = (multiplier*hashes[i+1] - y*t.charAt(i+s.length()) + t.charAt(i))%prime;
        }
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        int h = hashFunc(s);
        int p = hashFunc(t.substring(0,m));
        List<Integer> occurrences = new ArrayList<Integer>();
        precomputeHashes(s,t);
        for (int i = 0; i + m <= n; ++i) {
            if(h==hashes[i]){
                if(s.equals(t.substring(i,i+m))){
                    occurrences.add(i);
                }
            }
        }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}