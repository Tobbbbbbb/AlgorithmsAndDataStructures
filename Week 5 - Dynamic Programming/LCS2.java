import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int[][] matches = new int[a.length+1][b.length+1];
        for(int i=0; i<a.length+1; i++){
            matches[i][0] = 0;
        }
        for(int i=1; i<b.length+1; i++){
            matches[0][i] = 0;
        }
        int toAdd = 1;
        int max = 0;
        for(int i=1; i<a.length+1; i++){
            for(int j=1; j<b.length+1; j++){
                if(a[i-1] == b[j-1]){
                    toAdd = 1;
                } else {
                    toAdd = 0;
                }

                max = Math.max(matches[i-1][j], matches[i][j-1]);
                max = Math.max(max, matches[i-1][j-1] + toAdd);
                matches[i][j] = max;
            }
        }

        return matches[a.length][b.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

