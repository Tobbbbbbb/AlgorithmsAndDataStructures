import java.util.*;

public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
        ArrayList<Integer> aList = new ArrayList<Integer>();
        ArrayList<Integer> bList = new ArrayList<Integer>();
        for(int i=0; i<a.length; i++){
            aList.add(a[i]);
            bList.add(b[i]);
        }
        long result = 0;
        while(aList.size()>0){
            int maxA = 0;
            int maxB = 0;
            for(int i=1; i<aList.size(); i++){
                if(aList.get(i) > aList.get(maxA)){
                    maxA = i;
                }
                if(bList.get(i) > bList.get(maxB)){
                    maxB = i;
                }
            }
            result += (long)aList.remove(maxA) * (long)bList.remove(maxB);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

