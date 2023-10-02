import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if(n==0){
            return 0;
        } else if(n==1){
            return 1;
        }
        ArrayList<Long> fib = new ArrayList<Long>();
        fib.add((long)0);
        fib.add((long)1);
        for(int i = 2; i < Integer.MAX_VALUE; i++){
            fib.add((fib.get(i-1) + fib.get(i-2))%m);
            if(fib.get(i)==1 & fib.get(i-1)==0){
                break;
            }
        }
        return fib.get((int)(n%(fib.size()-2)));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

