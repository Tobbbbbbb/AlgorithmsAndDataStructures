import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        int fib[] = {0, 1, 2, 4, 7, 2, 0, 3, 4, 8, 3, 2, 6, 9, 6, 6, 3, 0, 4, 5, 0, 6, 7, 4, 2, 7, 0, 8, 9, 8, 8, 7, 6, 4, 1, 6, 8, 5, 4, 0, 5, 6, 2, 9, 2, 2, 5, 8, 4, 3, 8, 2, 1, 4, 6, 1, 8, 0, 9, 0};
        long fromInt = 0;
        if(from>0){
            fromInt = (long)fib[(int)((from-1)%fib.length)];
        }
        long toInt = (long)fib[(int)(to%fib.length)];
        return ((toInt-fromInt)+10)%10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumNaive(from, to));
    }
}

