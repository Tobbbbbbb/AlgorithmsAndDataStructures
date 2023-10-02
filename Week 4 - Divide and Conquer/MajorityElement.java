import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        if(right-left<=1){
            return a[left];
        }
        int mid = (left+right)/2;
        int b = getMajorityElement(a, left, mid-1);
        int c = getMajorityElement(a, mid, right);
        int bTot = 0;
        int cTot = 0;
        if(b==c){
            return b;
        }
        for(int i=left; i<right; i++){
            if(a[i]==b){
                bTot++;
            } else if(a[i]==c){
                cTot++;
            }
        }
        double maj = ((double) right - (double) left)/2.0;
        if(bTot>maj){
            return b;
        } else if(cTot>maj){
            return c;
        } else{
            return -1;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

