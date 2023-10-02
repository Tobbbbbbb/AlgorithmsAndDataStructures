import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class matching_with_mismatches {

    static long mod = (long)Math.pow(10,9) + 7;
    static long x = 29;
    private static long hashesText[];
    private static long hashesPattern[];
    private static int modifier = 'a'-2;
    private static long mults[];

    class Pair{
        int i;
        int j;

        public Pair(int i, int j){
            this.i=i;
            this.j=j;
        }
    }

    public List<Integer> solve(int k, String text, String pattern) {
        preComputeHashes(text, pattern);
        ArrayList<Integer> pos = new ArrayList<>();
        int max = text.length()-pattern.length()+1;
        int mismatches = 0;
        Stack<Pair> stack = new Stack<Pair>();
        int mid;
        for(int i=0; i<max; i++){
            mismatches=0;
            stack = new Stack<Pair>();
            if(textSubstring(i, i+pattern.length())!=hashesPattern[pattern.length()]){
                mismatches++;
                stack.add(new Pair(i, i+pattern.length()));
            }
            while(mismatches<=k  && !stack.empty()){
                Pair p = stack.pop();
                mid = (p.i + p.j)/2;
                if(((p.i+1)<mid) && (textSubstring(p.i, mid) != patternSubstring(p.i-i, mid-i))){
                    stack.add(new Pair(p.i, mid));
                }
                if(((mid+1)<p.j) && (textSubstring(mid, p.j) != patternSubstring(mid-i, p.j-i))){
                    stack.add(new Pair(mid, p.j));
                }
                if((p.i!=mid) && (p.j!=mid) && (textSubstring(p.i, mid) != patternSubstring(p.i-i, mid-i)) && (textSubstring(mid, p.j) != patternSubstring(mid-i, p.j-i))){
                    mismatches++;
                }
            }
            if(mismatches<=k){
                pos.add(i);
            }
        }
        return pos;
    }

    public static void preComputeHashes(String text, String pattern){
        hashesText = new long[text.length()+1];
        hashesPattern = new long[pattern.length()+1];
        mults = new long[pattern.length()+1];
        hashesText[0] = 0;
        hashesPattern[0] = 0;
        mults[0] = 1;
        for(int i=1; i<text.length()+1; i++){
            hashesText[i] = (x*hashesText[i-1] + reducedCharAt(text,i-1))%mod;
        }
        for(int i=1; i<pattern.length()+1; i++){
            hashesPattern[i] = (x*hashesPattern[i-1] + reducedCharAt(pattern,i-1))%mod;
            mults[i] = (x*mults[i-1])%mod;
        }
    }

    //returns hashvalue for text.substring(start,end);
    public static long textSubstring(int start, int end){
        return ((hashesText[end]-mults[end-start]*hashesText[start])%mod + mod)%mod;
    }

    public static long patternSubstring(int start, int end){
        return ((hashesPattern[end]-mults[end-start]*hashesPattern[start])%mod + mod)%mod;
    }

    public static int reducedCharAt(String str, int i){
        return str.charAt(i)-modifier;
    }



    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        in.lines().forEach(line -> {
            StringTokenizer tok = new StringTokenizer(line);
            int k = Integer.valueOf(tok.nextToken());
            String s = tok.nextToken();
            String t = tok.nextToken();
            List<Integer> ans = solve(k, s, t);
            out.format("%d ", ans.size());
            out.println(ans.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(" "))
            );
        });
        out.close();
    }

    static public void main(String[] args) {
        new matching_with_mismatches().run();
    }
}
