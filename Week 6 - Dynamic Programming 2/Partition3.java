import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {
        if(A.length<3){
            return 0;
        }
        int sum = 0;
        for(int i=0; i<A.length; i++){
            sum += A[i];
        }
        if(sum%3!=0){
            return 0;
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int i=A.length-1; i>=0; i--){
            temp.add(A[i]);
        }

        try{
            for(int i=0; i<3; i++){
                ArrayList<Integer> temp2 = optimalWeight(sum/3, temp);
                for(int j=0; j<temp2.size(); j++){
                    temp.remove(temp2.get(j));
                }
            }
        } catch(Exception e){
            return 0;
        }
        return 1;

    }

    static ArrayList<Integer> optimalWeight(int W, ArrayList<Integer> w) {
        int val;
        int[][] values = new int[W+1][w.size()+1];
        for(int i=1; i<=w.size(); i++){
            for(int j=1; j<=W; j++){
                values[j][i] = values[j][i-1];
                if(w.get(i-1) <= j){
                    val = values[j-w.get(i-1)][i-1] + w.get(i-1);
                    if(values[j][i] < val){
                        values[j][i] = val;
                    }
                    if(values[j][i] == W){
                        ArrayList<Integer> toRet = new ArrayList<Integer>();
                        int jTemp = j;
                        int iTemp = i;
                        while(iTemp>0){
                            if(values[jTemp][iTemp-1] == values[jTemp][iTemp-1]){
                            } else {
                                toRet.add(w.get(i-1));
                                jTemp-= w.get(i-1);
                            }
                            iTemp--;
                        }
                        return toRet;
                    }
                }
            }
        }
        return null;
    }

    private static Random random = new Random();

    private static int[] partition(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        int k = 0;
        for(int i = l + 1; i <= r; i++){
            if(a[i] < x){
                j++;
                int t1 = a[j];
                
                int t = a[i];
                a[i] = a[j+k];
                a[j+k] = a[j];
                a[j] = t;
            }else if(a[i]==x){
                k++;
                int t = a[i];
                a[i] = a[j+k];
                a[j+k] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
      int[] m = {j,j+k};
      return m;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m[] = partition(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        randomizedQuickSort(A, 0, n-1);
        System.out.println(partition3(A));
    }
}

