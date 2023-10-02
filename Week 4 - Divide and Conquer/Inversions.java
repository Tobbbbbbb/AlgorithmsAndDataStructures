import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        return numberOfInversions;
    }

    static long MergeSort(long[] a){
        long[] toRet = new long[2*a.length + 1];
        long numInversions = 0;
        if(a.length==1){
            toRet[0] = 0;
            toRet[1] = a[0];
            return numInversions;
        }
        int mid = a.length/2;
        long[] a1 = new long[mid];
        long[] a2 = new long[a.length-mid];
        for(int i=0; i<mid; i++){
            a1[i] = a[i];
        }
        for(int i=mid; i<a.length; i++){
            a2[i-mid] = a[i];
        }
        long[] sortA1 = MergeSort(a1);
        long[] sortA2 = MergeSort(a2);
        numInversions += sortA1[0];
        numInversions += sortA2[0];
        int a1Count = 0;
        int a2Count = 0;
        while(a1Count < a1.length-1 && a2Count < a2.length-1){

        }
        toRet[]

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

