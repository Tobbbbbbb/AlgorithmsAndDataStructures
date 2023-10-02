import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        int[][][] matches = new int[a.length+1][b.length+1][c.length+1];
        for(int i=0; i<a.length+1; i++){
            matches[i][0][0] = 0;
        }
        for(int i=1; i<b.length+1; i++){
            matches[0][i][0] = 0;
        }
        for(int i=1; i<c.length+1; i++){
            matches[0][0][i] = 0;
        }
        int toAdd = 1;
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        for(int i=1; i<a.length+1; i++){
            for(int j=1; j<b.length+1; j++){
                for(int k=1; k<c.length+1; k++){
                    if(a[i-1] == b[j-1] && a[i-1] == c[k-1]){
                        toAdd = 1;
                    } else {
                        toAdd = 0;
                    }

                    max1 = Math.max(matches[i-1][j][k], matches[i][j-1][k]);
                    max2 = Math.max(matches[i-1][j-1][k], matches[i][j-1][k-1]);
                    max3 = Math.max(matches[i-1][j][k-1], matches[i][j][k-1]);
                    max1 = Math.max(max1, matches[i-1][j-1][k-1] + toAdd);
                    max2 = Math.max(max2, max3);
                    max1 = Math.max(max1, max2);
                    matches[i][j][k] = max1;
                }
            }
        }

        return matches[a.length][b.length][c.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

