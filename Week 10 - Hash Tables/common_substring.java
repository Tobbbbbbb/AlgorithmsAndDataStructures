import java.util.*;
import java.io.*;

public class common_substring {
    static double m1 = Math.pow(10,9) + 7;
    static double m2 = Math.pow(10,9) + 9;
    static double x = 29;
    static int modifier = 'a'-2;
    //hashes[][0] is with str1 and m1, [1] is with str1 and m2, etc
    private static double hashes1[][];
    private static double hashes2[][];
    private static HashMap<Double, Integer> hashesLong1;
    private static HashMap<Double, Integer> hashesLong2;

    public class Answer {
        int i, j, len;
        Answer(int i, int j, int len) {
            this.i = i;
            this.j = j;
            this.len = len;
        }
    }

    public Answer solve(String s, String t) {
        Answer ans = new Answer(0, 0, 0);
        int left = 0;
        int right = Math.min(s.length(), t.length());
        int mid = (left+right)/2;
        boolean exists = false;
        boolean swapped = false;
        if(t.length() > s.length()){
            swapped = true;
            String temp = s;
            s = t;
            t = temp;
        }
        boolean fail=false;
        while(!fail){
            exists = false;
            preComputeHashes(s, t, mid);
            for(int i=0; i<hashes1.length; i++){
                    if(/*(hashesLong1.get(hashes1[i][0])==hashesLong2.get(hashes1[i][1])) &&*/ (hashesLong1.get(hashes1[i][0])!=null) && (hashesLong2.get(hashes1[i][1])!=null)){
                        exists=true;
                        ans = new Answer(i,Integer.valueOf(hashesLong1.get(hashes1[i][0])),mid);
                        if(swapped){
                            ans = new Answer(Integer.valueOf(hashesLong1.get(hashes1[i][0])),i, mid);
                        }
                        break;
                    }
                }
            if(left==right){
                fail=true;
            }
            if(exists){
                left=mid+1;
            } else {
                if(left!=mid){
                    right=mid-1;
                } else {
                    right=mid;
                }
            }
            mid=(left+right)/2;
        }
        return ans;
    }

    public static void preComputeHashes(String s1, String s2, int l){
        hashes1 = new double[s1.length()-l+1][2];
        hashes2 = new double[s2.length()-l+1][2];
        hashes1[0][0] = firstHashFunc(s1.substring(0,l), m1);
        hashes1[0][1] = firstHashFunc(s1.substring(0,l), m2);
        hashes2[0][0] = firstHashFunc(s2.substring(0,l), m1);
        hashes2[0][1] = firstHashFunc(s2.substring(0,l), m2);
        double y=1;
        double z=1;
        for(int i=0; i<l; i++){
            y = (y*x)%m1;
            z = (z*x)%m2;
        }
        for(int i=l; i<s1.length(); i++){
            hashes1[i-l+1][0] = (x*hashes1[i-l][0] - y*(double)reducedCharAt(s1, i-l) + (double)reducedCharAt(s1, i))%m1;
            hashes1[i-l+1][1] = (x*hashes1[i-l][1] - z*(double)reducedCharAt(s1, i-l) + (double)reducedCharAt(s1, i))%m2;
            hashes1[i-l+1][0] = (hashes1[i-l+1][0] + m1)%m1;
            hashes1[i-l+1][1] = (hashes1[i-l+1][1] + m2)%m2;
        }
        for(int i=l; i<s2.length(); i++){
            hashes2[i-l+1][0] = (x*hashes2[i-l][0] - y*(double)reducedCharAt(s2, i-l) + (double)reducedCharAt(s2, i))%m1;
            hashes2[i-l+1][1] = (x*hashes2[i-l][1] - z*(double)reducedCharAt(s2, i-l) + (double)reducedCharAt(s2, i))%m2;
            hashes2[i-l+1][0] = (hashes2[i-l+1][0] + m1)%m1;
            hashes2[i-l+1][1] = (hashes2[i-l+1][1] + m2)%m2;
        }

        hashesLong1 = new HashMap<Double, Integer>();
        hashesLong2 = new HashMap<Double, Integer>();

        for(int i=0; i<hashes2.length; i++){
            hashesLong1.put(hashes2[i][0], i);
            hashesLong2.put(hashes2[i][1], i);
        }
    }

    public static double firstHashFunc(String str, double prime){
        double hash=0;
        for(int i=0; i<str.length(); i++){
            hash = (hash*x + reducedCharAt(str, i)) % prime;
        }
        return hash;
    }

    public static int reducedCharAt(String str, int i){
        return str.charAt(i)-modifier;
    }

    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        in.lines().forEach(line -> {
            StringTokenizer tok = new StringTokenizer(line);
            String s = tok.nextToken();
            String t = tok.nextToken();
            Answer ans = solve(s, t);
            out.format("%d %d %d\n", ans.i, ans.j, ans.len);
        });
        out.close();
    }

    static public void main(String[] args) {
        new common_substring().run();
    }
}